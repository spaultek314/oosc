package com.oosc.evaluation;

import com.oosc.evaluation.AgentModels.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/evaluations")
public class EvaluationController {
    private final EvaluationService service;
    public EvaluationController(EvaluationService service) { this.service = service; }
    public record EvaluationRequest(@Valid AgentDefinition agent, @Min(1) @Max(1000) int scenarioCount) {}
    public record EvaluationResponse(List<Scenario> scenarios, List<ExecutionResult> executions, ReliabilityReport report) {}
    @PostMapping
    public EvaluationResponse evaluate(@RequestBody @Valid EvaluationRequest request) {
        var scenarios = service.generate(request.agent(), request.scenarioCount());
        var executions = scenarios.stream().map(service::execute).toList();
        return new EvaluationResponse(scenarios, executions, service.report(request.agent(), executions));
    }
    @PostMapping("/replay")
    public ExecutionResult replay(@RequestBody Scenario scenario) { return service.execute(scenario); }
}
