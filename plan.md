# OOSC Implementation Plan

## Module boundaries

- `domain`: immutable agent, scenario, trace, outcome, failure mode, and report contracts.
- `application`: registration, deterministic scenario generation, sandbox validation, evaluation, replay, scoring, and regression reporting.
- `web`: REST controllers, JSON error handling, and the static browser dashboard.

## Delivery sequence

1. Define immutable domain contracts.
2. Generate realistic and adversarial scenarios.
3. Validate execution traces through a bounded sandbox.
4. Classify failures and compute scores.
5. Track outcomes and version deltas in memory.
6. Expose JSON APIs and the browser dashboard.
7. Add focused tests and CI validation.
