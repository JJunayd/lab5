package manager;

import products.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class DatabaseManager {
    private static String username;
    private static String password;
    private static String url;
    static{
        try {
            Properties info = new Properties();
            info.load(new FileInputStream("src/db.cfg"));
            String dname = (String) info.get("Dname");
            username = (String) info.get("username");
            password = (String) info.get("password");
            url = (String) info.get("URL");
            Class.forName(dname);
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Ошибка при выполнении запроса к базе данных. Коллекция не загружена.");
        } catch(IOException e){
            System.out.println("Ошибка при подключении к базе данных. Коллекция не загружена.");
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка при загрузке драйвера. Коллекция не загружена");
        }
    }
    private static Connection connection;

    public static void loadCollection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement selectAll = connection.prepareStatement(DatabaseRequests.LOAD.getReq());

            ResultSet rs = selectAll.executeQuery();
            while (rs.next()) {
                long addressId = rs.getLong("address_id");
                String street = rs.getString("street");
                String zipCode = rs.getString("zip_code");

                Address address = new Address(addressId, street, zipCode);

                long orgId = rs.getLong("org_id");
                String orgName = rs.getString("org_name");
                long employeesCount = rs.getLong("employees_count");
                OrganizationType type = OrganizationType.valueOf(rs.getString("organization_type").toUpperCase());

                Organization manufacturer = new Organization(orgId, orgName, employeesCount, type, address);

                long coordId = rs.getLong("coord_id");
                float x = rs.getFloat("x");
                int y = rs.getInt("y");

                Coordinates coordinates = new Coordinates(coordId, x, y);

                String creator = rs.getString("creator");

                long id = rs.getLong("id");
                String name = rs.getString("name");
                Date creationDate = rs.getTimestamp("creation_date");
                long price = rs.getLong("price");
                UnitOfMeasure unitOfMeasure = UnitOfMeasure.valueOf(rs.getString("unit_of_measure").toUpperCase());

                Product product = new Product(id, name, coordinates, creationDate, price, unitOfMeasure, manufacturer, creator);

                CollectionManager.add(product);

            }

        } catch (SQLException e) {
            System.out.println("Ошибка при выполнении запроса к базе данных. Коллекция не загружена.");
        }
    }
    public static boolean addToDatabase(Product product) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement insertCoord = connection.prepareStatement(DatabaseRequests.INSERT_COORD.getReq());

            insertCoord.setFloat(1, product.getCoordinates().getX());
            insertCoord.setInt(2, product.getCoordinates().getY());

            insertCoord.executeUpdate();

            PreparedStatement insertAddr = connection.prepareStatement(DatabaseRequests.INSERT_ADDR.getReq());

            insertAddr.setString(1, product.getManufacturer().getOfficialAddress().getStreet());
            insertAddr.setString(2, product.getManufacturer().getOfficialAddress().getZipCode());

            insertAddr.executeUpdate();

            PreparedStatement insertMan = connection.prepareStatement(DatabaseRequests.INSERT_MAN.getReq());

            insertMan.setString(1, product.getManufacturer().getName());
            insertMan.setLong(2, product.getManufacturer().getEmployeesCount());
            insertMan.setString(3, product.getManufacturer().getType().toString());

            insertMan.executeUpdate();

            PreparedStatement insertProduct = connection.prepareStatement(DatabaseRequests.INSERT_PRODUCT.getReq());

            insertProduct.setString(1, product.getName());
            insertProduct.setLong(2, product.getPrice());
            insertProduct.setString(3, product.getUnitOfMeasure().toString());
            insertProduct.setString(4, product.getCreator());
            try {
                product.getId();
            }catch(NullPointerException e){
                product.updateId();
            }
            long newId = product.getId();
                while (hasId(newId)) {
                    newId+=1;
                }
            insertProduct.setLong(5, newId);
            insertProduct.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Ошибка при выполнении запроса к базе данных. Элемент не добавлен.");
            e.printStackTrace();
            return false;
        }
    }
    public static boolean removeFromDatabase(Product product) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement removeProduct = connection.prepareStatement(DatabaseRequests.REMOVE_PRODUCT.getReq());

            removeProduct.setLong(1, product.getId());

            removeProduct.executeUpdate();

            PreparedStatement removeCoord = connection.prepareStatement(DatabaseRequests.REMOVE_COORD.getReq());

            removeCoord.setLong(1, product.getCoordinates().getId());

            removeCoord.executeUpdate();

            PreparedStatement removeMan = connection.prepareStatement(DatabaseRequests.REMOVE_MAN.getReq());

            removeMan.setLong(1, product.getManufacturer().getId());

            removeMan.executeUpdate();

            PreparedStatement removeAddr = connection.prepareStatement(DatabaseRequests.REMOVE_ADDR.getReq());

            removeAddr.setLong(1, product.getManufacturer().getOfficialAddress().getId());

            removeAddr.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Ошибка при выполнении запроса к базе данных. Элемент не удален.");
            return false;
        }
    }
    public static void updateId(Product product) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement updateId = connection.prepareStatement(DatabaseRequests.UPDATE_ID.getReq());
            updateId.setLong(2, product.getId());
            do{product.updateId();}
            while(hasId(product.getId()));

            updateId.setLong(1, product.getId());
            updateId.execute();

        } catch (SQLException e) {
            System.out.println("Ошибка при выполнении запроса к базе данных. Элемент не обновлен.");
        }
    }
    private static boolean hasId(long id){
        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement hasId = connection.prepareStatement(DatabaseRequests.HAS_ID.getReq());

            hasId.setLong(1, id);

            ResultSet hasIdRes = hasId.executeQuery();

            if(hasIdRes.next()){
                if(hasIdRes.getInt("idMatch") > 0){
                    connection.close();
                    return true;
                }
                else{
                    connection.close();
                    return false;
                }
            }
            return false;

        } catch (SQLException e) {
            System.out.println("Ошибка при выполнении запроса к базе данных. Элемент не обновлен.");
            return false;
        }
    }
    public static void clearAll() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement clear = connection.prepareStatement(DatabaseRequests.CLEAR_ALL.getReq());
            clear.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ошибка при выполнении запроса к базе данных. База не очищена.");
        }
    }
    public static boolean clear(String curUser){
        ArrayList<Product> myProducts = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(url, username, password);
                CollectionManager.collection.stream().filter(p->p.getCreator().equals(curUser)).forEach(myProducts::add);

            for (Product p:myProducts) {
                PreparedStatement clearProduct = connection.prepareStatement(DatabaseRequests.CLEAR_PRODUCT.getReq());
                clearProduct.setLong(1, p.getId());
                PreparedStatement clearMan = connection.prepareStatement(DatabaseRequests.CLEAR_MAN.getReq());
                clearMan.setLong(1, p.getManufacturer().getId());
                PreparedStatement clearCoord = connection.prepareStatement(DatabaseRequests.CLEAR_COORD.getReq());
                clearCoord.setLong(1, p.getCoordinates().getId());
                PreparedStatement clearAddr = connection.prepareStatement(DatabaseRequests.CLEAR_ADDR.getReq());
                clearAddr.setLong(1, p.getManufacturer().getOfficialAddress().getId());
                clearProduct.executeUpdate();
                clearMan.executeUpdate();
                clearCoord.executeUpdate();
                clearAddr.executeUpdate();
            }

            return true;
        } catch (SQLException e) {
            System.out.println("Ошибка при выполнении запроса к базе данных. База не очищена.");
            return false;
        }
    }
    public static boolean login(String user, String pass){
        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement checkLog = connection.prepareStatement(DatabaseRequests.CHECK_LOG.getReq());

            checkLog.setString(1, user);
            checkLog.setString(2, pass);

            ResultSet rs = checkLog.executeQuery();
            boolean auth = false;
            while(rs.next()) {
                if (rs.getInt("auth") > 0) {
                    auth = true;
                } else {
                    auth = false;
                }
            }
            return auth;

        } catch (SQLException e) {
            System.out.println("Ошибка при выполнении запроса к базе данных. Не удалось проверить данные о пользователе.");
            e.printStackTrace();
            return false;
        }
    }
    public static boolean register(String user, String pass){
        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement reg = connection.prepareStatement(DatabaseRequests.REGISTER.getReq());

            reg.setString(1, user);
            reg.setString(2, pass);

            int rs = reg.executeUpdate();
                if (rs > 0) {
                    return true;
                } else {
                    return false;
                }


        } catch (SQLException e) {
            System.out.println("Ошибка при выполнении запроса к базе данных. Не удалось зарегистрировать нового пользователя.");
            return false;
        }
    }
}
