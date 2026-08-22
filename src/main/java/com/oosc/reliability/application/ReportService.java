package com.oosc.reliability.application;

import com.oosc.reliability.domain.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    private final EvaluationEngine engine;
    private final AgentRegistry agents;

    public ReportService(EvaluationEngine engine, AgentRegistry agents) {
        this.engine = engine;
        this.agents = agents;
    }

    public ReliabilityReport report(UUID id) {
        Agent agent = agents.get(id);
        List<EvaluationOutcome> outcomes = engine.forAgent(id);
        var counts = new EnumMap<FailureMode, Long>(FailureMode.class);
        outcomes.forEach(o -> counts.merge(o.failureMode(), 1L, Long::sum));
        double score = outcomes.stream().mapToDouble(EvaluationOutcome::score).average().orElse(0);
        var regressions = new ArrayList<ReliabilityReport.Regression>();
        var versions = outcomes.stream().map(EvaluationOutcome::agentVersion).distinct().sorted().toList();
        for (int i = 1; i < versions.size(); i++) {
            String previous = versions.get(i - 1);
            String current = versions.get(i);
            double previousScore = average(outcomes, previous);
            double currentScore = average(outcomes, current);
            double delta = currentScore - previousScore;
            regressions.add(new ReliabilityReport.Regression(previous, current, previousScore, currentScore, delta));
        }
        return new ReliabilityReport(agent.id(), agent.name(), agent.version(), outcomes.size(), outcomes.stream().filter(o -> o.failureMode() != FailureMode.PASS).count(), score, counts, List.copyOf(regressions));
    }

    private double average(List<EvaluationOutcome> values, String version) {
        return values.stream().filter(o -> o.agentVersion().equals(version)).mapToDouble(EvaluationOutcome::score).average().orElse(0);
    }
}
