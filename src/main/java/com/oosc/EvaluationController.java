package com.oosc;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class EvaluationController {
    private final EvaluationService service;

    public EvaluationController(EvaluationService service) {
        this.service = service;
    }

    @PostMapping("/agents")
    @ResponseStatus(HttpStatus.CREATED)
    public EvaluationDomain.AgentDefinition register(@Valid @RequestBody EvaluationDomain.AgentDefinition request) {
        return service.register(request);
    }

    @PostMapping("/scenarios")
    public List<EvaluationDomain.Scenario> scenarios(@Valid @RequestBody EvaluationDomain.ScenarioRequest request) {
        return service.generate(request);
    }

    @PostMapping("/evaluations")
    @ResponseStatus(HttpStatus.CREATED)
    public EvaluationDomain.EvaluationResult evaluate(@Valid @RequestBody EvaluationDomain.ExecutionRequest request) {
        return service.evaluate(request);
    }

    @GetMapping("/agents/{agentId}/report")
    public EvaluationDomain.ReliabilityReport report(@PathVariable UUID agentId) {
        return service.report(agentId);
    }
}
