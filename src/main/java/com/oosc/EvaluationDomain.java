package com.oosc;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public final class EvaluationDomain {
    private EvaluationDomain() {}

    public enum FailureMode { PASS, TOOL_CALL_LOOP, HALLUCINATED_CONFIDENCE, DESTRUCTIVE_ACTION, GOAL_DRIFT, TIMEOUT, SANDBOX_ERROR }

    public record AgentDefinition(UUID id, String name, String version, String systemPrompt, List<ToolDefinition> tools) {}
    public record ToolDefinition(String name, boolean destructive, String description) {}
    public record Scenario(UUID id, String title, String prompt, boolean adversarial, String expectedInvariant) {}
    public record ScenarioRequest(String agentName, String version, String taskDomain, String prompt, List<ToolDefinition> tools, int count) {}
    public record ExecutionRequest(UUID agentId, UUID scenarioId, String agentOutput, List<ToolCall> toolCalls, long durationMs) {}
    public record ToolCall(String tool, String arguments, boolean reversible) {}
    public record EvaluationResult(UUID evaluationId, UUID agentId, UUID scenarioId, FailureMode failureMode, double score, String rationale, Instant evaluatedAt) {}
    public record ReliabilityReport(UUID agentId, String version, long executions, long failures, double reliabilityScore, List<FailureSummary> failuresByMode) {}
    public record FailureSummary(FailureMode mode, long count) {}
}
