package com.oosc.evaluation;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public final class AgentModels {
    private AgentModels() {}
    public record AgentDefinition(String name, String version, String systemPrompt, String domain, List<String> tools) {}
    public record Scenario(UUID id, String category, String prompt, boolean destructiveProbe, List<String> expectedToolCalls) {}
    public record TraceStep(int index, String type, String value, Instant at) {}
    public record ExecutionResult(UUID scenarioId, List<TraceStep> trace, String finalAnswer, FailureMode failureMode, boolean passed) {}
    public record ReliabilityReport(String agent, String version, int total, int passed, double score, java.util.Map<FailureMode, Long> failures) {}
}
