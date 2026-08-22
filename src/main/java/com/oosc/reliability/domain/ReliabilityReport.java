package com.oosc.reliability.domain;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public record ReliabilityReport(UUID agentId, String agentName, String version, long executions, long failures, double score, Map<FailureMode, Long> failureCounts, List<Regression> regressions) {
    public record Regression(String previousVersion, String currentVersion, double previousScore, double currentScore, double delta) {}
}
