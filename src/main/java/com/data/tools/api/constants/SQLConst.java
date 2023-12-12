package com.data.tools.api.constants;

public class SQLConst {

    public static String getSelectBySchemeAndTable(String schemaName, String tableName) {
        return String.format("SELECT * FROM %s.%s", schemaName, tableName);
    }
}
