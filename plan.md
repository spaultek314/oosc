# OOSC Implementation Plan

## Modules

- `domain`: immutable agent, scenario, trace, outcome, and report contracts.
- `application`: registration, generation, evaluation, replay, scoring, and regression services.
- `infrastructure`: deterministic sandbox simulator and in-memory stores.
- `web`: REST controllers plus a static single-page dashboard.

## Delivery sequence

1. Define immutable domain model and ports.
2. Implement deterministic scenario generation and sandbox execution.
3. Implement failure classification and reliability scoring.
4. Expose REST endpoints and browser dashboard.
5. Add tests and CI.
6. Validate with Maven before merge.
