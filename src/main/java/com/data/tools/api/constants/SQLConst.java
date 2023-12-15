package com.data.tools.api.constants;

public class SQLConst {

    public static String getSelectBySchemeAndTable(String schemaName, String tableName) {
        return String.format("SELECT * FROM %s.%s", schemaName, tableName);
    }

    public static  String getSchemas() {
        return "SELECT username as Schemas FROM all_users";
    }

    public static String getTablesBySchema(String schemaName) {
        return String.format("SELECT table_name as Tables FROM all_tables WHERE owner = '%s'",schemaName);
    }
    public static String getColumnsByTable(String schemaName, String tableName) {
        return String.format("SELECT column_name,data_type FROM all_tab_columns WHERE owner = '%s' AND table_name = '%s';",schemaName,tableName);
    }
}
