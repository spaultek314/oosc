# OOSC Agent Reliability Platform

End-to-end Java 21 and Spring Boot 3 application for continuous evaluation of autonomous agents.

## Contract

The platform implements:

1. Agent registration with immutable version identity.
2. Deterministic scenario generation from prompt, domain, and declared tools.
3. Adversarial pressure scenarios for destructive and irreversible actions.
4. Sandboxed simulated execution with bounded tool-call budgets.
5. Trace capture and deterministic replay by scenario identifier and agent version.
6. Failure classification for tool-call loops, hallucinated confidence, destructive actions, goal drift, timeout, and sandbox errors.
7. Reliability scorecards and version regression detection.
8. Browser dashboard and JSON REST API served by the same Spring Boot application.

## Validation contract

A change is complete only when these commands exit with status 0:

```bash
./mvnw -B verify
```

The CI workflow executes the same command on pushes and pull requests.
