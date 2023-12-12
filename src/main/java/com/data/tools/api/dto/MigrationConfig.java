package com.data.tools.api.dto;

public record MigrationConfig(Long id, Long sourceId, Long targetId, String name, String desc) {
}
