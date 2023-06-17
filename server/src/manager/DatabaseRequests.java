package manager;

public enum DatabaseRequests {
    LOAD("select p.*, org.*, coord.*, addr.* FROM product p JOIN coordinates coord ON p.coordinates = coord.coord_id JOIN organization org ON p.manufacturer = org.org_id JOIN address addr ON org.official_address = addr.address_id;"),
    INSERT_COORD("INSERT INTO coordinates(x, y) VALUES(?, ?);"),
    INSERT_ADDR("INSERT INTO address(street, zip_code) VALUES(?, ?);"),
    INSERT_MAN("INSERT INTO organization(org_name, employees_count, organization_type) VALUES(?, ?, CAST(? AS organization_type));"),
    INSERT_PRODUCT("INSERT INTO product(name, price, unit_of_measure, creator, id) VALUES(?, CAST(? AS bigint), CAST(? AS unit_of_measure), ?, CAST(? AS BIGINT));"),
    REMOVE_PRODUCT("DELETE FROM product WHERE id = ?;"),
    REMOVE_COORD("DELETE FROM coordinates WHERE coord_id = ?;"),
    REMOVE_MAN("DELETE FROM organization WHERE org_id = ?;"),
    REMOVE_ADDR("DELETE FROM address WHERE address_id = ?;"),
    UPDATE_ID("UPDATE product SET id = ? WHERE id = ?;"),
    HAS_ID("SELECT COUNT(id) AS idMatch FROM product WHERE id = ?;"),
    CLEAR_ALL("DELETE FROM product; DELETE FROM organization; DELETE FROM coordinates; DELETE FROM address"),
    CLEAR_PRODUCT("DELETE FROM product WHERE id = ?;"),
    CLEAR_MAN("DELETE FROM organization WHERE org_id = ?;"),
    CLEAR_COORD("DELETE FROM coordinates WHERE coord_id = ?;"),
    CLEAR_ADDR("DELETE FROM address WHERE address_id = ?;"),
    CHECK_LOG("SELECT COUNT(*) AS auth FROM client WHERE client_name = ? AND password = ?;"),
    REGISTER("INSERT INTO client(client_name, password) VALUES(?, ?)"),

    ;
    private String req;
    DatabaseRequests(String request){
        this.req = request;
    }

    public String getReq() {
        return req;
    }
}

