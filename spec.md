# OOSC Product Specification

## Objective

Provide a web-accessible platform that generates adversarial tests for autonomous AI agents, evaluates deterministic execution traces, detects unsafe failure modes, replays outcomes, and reports reliability by agent version.

## Functional requirements

- Register an agent with name, semantic version, domain, system prompt, and declared tools.
- Generate up to 100 scenarios covering realistic work, tool-call loops, destructive-action guardrails, hallucinated confidence, goal drift, and timeout pressure.
- Evaluate supplied agent output and tool traces under bounded safety rules.
- Classify every evaluation into a fixed failure taxonomy.
- Score each evaluation from 0 to 100.
- Retain evaluations for the process lifetime and expose deterministic replay of recorded outcomes.
- Produce reliability reports with execution count, failure count, weighted score, failure breakdown, and version deltas.
- Serve a browser dashboard from the same Spring Boot process.
- Expose health and metrics through Actuator.

## Non-functional requirements

- Java 21 and Spring Boot 3.5.
- Immutable domain records.
- No domain-to-web or domain-to-infrastructure dependency.
- Virtual threads enabled.
- Required identifiers and numeric bounds are validated at the application boundary.
- Failure responses are JSON and do not leak server internals.
- CI runs `mvn -B verify` with Java 21.
