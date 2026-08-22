# OOSC Engineering Constitution

## Architecture

The application is a modular Spring Boot 3 monolith. Domain records and enums do not depend on Spring or infrastructure. HTTP controllers depend on application services. Application services depend on domain types and ports. In-memory infrastructure adapters implement ports for deterministic local execution.

## Runtime invariants

- Java 21 is required.
- Public domain values are immutable records or enums.
- Evaluation is deterministic for the same agent version and scenario.
- Every execution has a bounded tool-call budget and timeout.
- Destructive actions are always classified as failures.
- Reliability is computed from persisted evaluation outcomes, not generated text.
- Files are kept cohesive and under 200 lines.

## Verification

`./mvnw -B verify` is the authoritative local validation command. GitHub Actions runs the same command.
