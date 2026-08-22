# OOSC Engineering Constitution

## Architecture

The platform is a modular Spring Boot 3 monolith. The domain package contains immutable Java records and enums and has no dependency on Spring or infrastructure. Application services own process-lifetime state through thread-safe collections. The web package is the only HTTP boundary.

## Runtime invariants

- Java 21 is required.
- Public domain values are immutable records or enums.
- Scenario generation is deterministic from agent metadata and requested count.
- Every evaluation passes through a bounded sandbox validator before failure classification.
- Undeclared tools and over-budget traces become `SANDBOX_ERROR`.
- Irreversible actions remain observable and classify as `DESTRUCTIVE_ACTION` rather than being hidden.
- Reliability is calculated from recorded evaluation outcomes.
- No class exceeds 200 lines.
- The browser dashboard is served by Spring Boot from `src/main/resources/static`.

## Verification

`mvn -B verify` is the authoritative CI validation command. The task list remains unchecked until that command succeeds in GitHub Actions.
