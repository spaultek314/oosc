package com.oosc.reliability;

import com.oosc.reliability.application.*;
import com.oosc.reliability.domain.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ReliabilityEngineTest {
    private final AgentRegistry registry = new AgentRegistry();
    private final ScenarioGenerator generator = new ScenarioGenerator();
    private final EvaluationEngine engine = new EvaluationEngine();

    @Test void destructiveActionsFail() {
        Agent agent = registry.register(new Agent(null, "ops", "1.0.0", "ops", "safely act", List.of(new ToolDefinition("delete_file", true, "delete"))));
        Scenario s = generator.generate(agent, 3).stream().filter(x -> x.category().equals("DESTRUCTIVE_GUARDRAIL")).findFirst().orElseThrow();
        EvaluationOutcome o = engine.evaluate(agent, s, "proceed", List.of(new ToolCall("delete_file", "{}", false, null)), 100);
        assertThat(o.failureMode()).isEqualTo(FailureMode.DESTRUCTIVE_ACTION);
        assertThat(o.score()).isZero();
    }

    @Test void repeatedToolCallsAreDetected() {
        Agent agent = registry.register(new Agent(null, "loop", "1.0.0", "ops", "act", List.of(new ToolDefinition("search", false, "search"))));
        Scenario s = generator.generate(agent, 2).get(1);
        List<ToolCall> trace = java.util.stream.IntStream.range(0, 8).mapToObj(i -> new ToolCall("search", "{}", true, null)).toList();
        EvaluationOutcome o = engine.evaluate(agent, s, "retry", trace, 100);
        assertThat(o.failureMode()).isEqualTo(FailureMode.TOOL_CALL_LOOP);
    }

    @Test void confidentClaimsAreDetected() {
        Agent agent = registry.register(new Agent(null, "sales", "1.0.0", "sales", "act", List.of()));
        Scenario s = generator.generate(agent, 1).getFirst();
        EvaluationOutcome o = engine.evaluate(agent, s, "I am 100% certain this is true", List.of(), 100);
        assertThat(o.failureMode()).isEqualTo(FailureMode.HALLUCINATED_CONFIDENCE);
    }
}
