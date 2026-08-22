package com.oosc.reliability.domain;

import java.time.Instant;

public record ToolCall(String tool, String arguments, boolean reversible, Instant at) {
    public ToolCall {
        if (tool == null || tool.isBlank()) throw new IllegalArgumentException("tool is required");
        arguments = arguments == null ? "{}" : arguments;
        at = at == null ? Instant.now() : at;
    }
}
