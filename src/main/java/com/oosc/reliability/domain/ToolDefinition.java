package com.oosc.reliability.domain;

public record ToolDefinition(String name, boolean destructive, String description) {
    public ToolDefinition {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("tool name is required");
        description = description == null ? "" : description;
    }
}
