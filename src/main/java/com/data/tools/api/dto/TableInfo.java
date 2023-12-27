package com.data.tools.api.dto;

public record TableInfo(String columnName, String dataType, int dataLength,String nullable, String constraints) {
}
