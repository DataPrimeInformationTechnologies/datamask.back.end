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
        return String.format("SELECT atc.COLUMN_NAME, atc.DATA_TYPE, atc.DATA_LENGTH, atc.NULLABLE,( SELECT LISTAGG(ACC.CONSTRAINT_NAME, ', ') WITHIN GROUP (ORDER BY ACC.CONSTRAINT_NAME)FROM ALL_CONS_COLUMNS ACC WHERE ACC.TABLE_NAME = '%s' AND ACC.COLUMN_NAME = atc.COLUMN_NAME AND ACC.OWNER = '%s') AS CONSTRAINTS FROM ALL_TAB_COLUMNS atc WHERE atc.OWNER = '%s' AND atc.TABLE_NAME = '%s'",tableName,schemaName,schemaName,tableName);
    }
}
