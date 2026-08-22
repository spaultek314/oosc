package com.oosc.reliability.web;

import com.oosc.reliability.application.*;
import com.oosc.reliability.domain.*;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ReliabilityController {
    private final AgentRegistry agents;
    private final ScenarioGenerator generator;
    private final EvaluationEngine engine;
    private final ReportService reports;

    public ReliabilityController(AgentRegistry agents, ScenarioGenerator generator, EvaluationEngine engine, ReportService reports) {
        this.agents = agents;
        this.generator = generator;
        this.engine = engine;
        this.reports = reports;
    }

    @PostMapping("/agents")
    @ResponseStatus(HttpStatus.CREATED)
    public Agent register(@RequestBody Agent request) { return agents.register(request); }

    @PostMapping("/agents/{agentId}/scenarios")
    public List<Scenario> scenarios(@PathVariable UUID agentId, @RequestParam(defaultValue = "12") int count) {
        return generator.generate(agents.get(agentId), count);
    }

    @PostMapping("/evaluations")
    @ResponseStatus(HttpStatus.CREATED)
    public EvaluationOutcome evaluate(@RequestBody EvaluationRequest request) {
        Agent agent = agents.get(request.agentId());
        Scenario scenario = generator.get(request.scenarioId());
        if (!scenario.agentId().equals(agent.id())) throw new IllegalArgumentException("scenario does not belong to agent");
        return engine.evaluate(agent, scenario, request.output(), request.trace(), request.durationMs());
    }

    @GetMapping("/evaluations/{evaluationId}")
    public EvaluationOutcome replay(@PathVariable UUID evaluationId) { return engine.replay(evaluationId); }

    @GetMapping("/agents/{agentId}/report")
    public ReliabilityReport report(@PathVariable UUID agentId) { return reports.report(agentId); }

    public record EvaluationRequest(UUID agentId, UUID scenarioId, String output, List<ToolCall> trace, long durationMs) {
        public EvaluationRequest {
            if (agentId == null || scenarioId == null) throw new IllegalArgumentException("agentId and scenarioId are required");
            if (durationMs < 0) throw new IllegalArgumentException("durationMs cannot be negative");
        }
    }
}
