package com.data.tools.api.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbConnectionWithData {
    private long conn_id;
    private List<String> schemas;
    private String selectedSchema;
    private List<String> tableNames;
    private String selectedTable;
    private List<String> columnNames;

}
