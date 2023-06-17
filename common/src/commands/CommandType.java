package commands;

public enum CommandType {
    CLEAR("clear"),
    EXIT("exit"),
    HEAD("head"),
    HELP("help"),
    INFO("info"),
    PRINT_DESCENDING("print_descending"),
    SHOW("show"),
    SUM_OF_PRICE("sum_of_price"),
    ADD("add"),
    ADD_IF_MIN("add_if_min"),
    REMOVE_BY_ID("remove_by_id"),
    UPDATE_TASK("update_task"),
    UPDATE_ID("update_id"),
    EXECUTE_SCRIPT("execute_script"),
    REMOVE_LOWER("remove_lower"),
    FILTER_GREATER_THAN_PRICE("filter_greater_than_price"),
    SAVE("save"),
    LOGIN("login"),
    REGISTER("register"),
    LOGOUT("logout");
    private final String name;
    CommandType(String name) {
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}
