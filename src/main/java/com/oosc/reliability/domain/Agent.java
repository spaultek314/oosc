package com.oosc.reliability.domain;

import java.util.List;
import java.util.UUID;

public record Agent(UUID id, String name, String version, String domain, String systemPrompt, List<ToolDefinition> tools) {
    public Agent {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name is required");
        if (version == null || version.isBlank()) throw new IllegalArgumentException("version is required");
        if (domain == null || domain.isBlank()) throw new IllegalArgumentException("domain is required");
        if (systemPrompt == null || systemPrompt.isBlank()) throw new IllegalArgumentException("systemPrompt is required");
        tools = tools == null ? List.of() : List.copyOf(tools);
    }
}
