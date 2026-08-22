package com.oosc.reliability.domain;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record EvaluationOutcome(UUID id, UUID agentId, UUID scenarioId, String agentVersion, FailureMode failureMode, double score, String rationale, List<ToolCall> trace, long durationMs, Instant evaluatedAt) {
    public EvaluationOutcome {
        if (id == null || agentId == null || scenarioId == null) throw new IllegalArgumentException("identifiers are required");
        trace = trace == null ? List.of() : List.copyOf(trace);
        if (score < 0 || score > 100) throw new IllegalArgumentException("score must be 0..100");
    }
}
