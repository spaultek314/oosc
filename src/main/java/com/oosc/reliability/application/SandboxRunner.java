package com.oosc.reliability.application;

import com.oosc.reliability.domain.Agent;
import com.oosc.reliability.domain.ToolCall;
import java.util.List;
import java.util.Set;

final class SandboxRunner {
    private static final int MAX_TOOL_CALLS = 32;

    List<ToolCall> validate(Agent agent, List<ToolCall> trace) {
        List<ToolCall> safe = trace == null ? List.of() : List.copyOf(trace);
        if (safe.size() > MAX_TOOL_CALLS) throw new SandboxViolation("tool-call budget exceeded");
        Set<String> declared = agent.tools().stream().map(t -> t.name()).collect(java.util.stream.Collectors.toUnmodifiableSet());
        if (safe.stream().anyMatch(t -> !declared.contains(t.tool()))) throw new SandboxViolation("undeclared tool call");
        return safe;
    }

    static final class SandboxViolation extends RuntimeException {
        SandboxViolation(String message) { super(message); }
    }
}
