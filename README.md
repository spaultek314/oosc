# AI Agent Evaluation and Reliability Engine

Java 21 / Spring Boot 3 implementation for Problem Statement 4.

## Delivered capabilities

- Agent registration and versioning.
- Realistic/adversarial scenario generation from prompt, domain, and tool metadata.
- Sandboxed execution contract represented by an execution request and tool-call trace.
- Failure taxonomy covering tool-call loops, hallucinated confidence, destructive actions, goal drift, timeout, and sandbox errors.
- Reliability scoring and regression-ready report model.
- Virtual threads and Actuator metrics/health.
- Kafka-ready infrastructure via Docker Compose.
- Deterministic unit coverage for destructive-action detection.

## Run

```bash
mvn spring-boot:run
```

## Endpoints

`POST /api/v1/agents`

`POST /api/v1/scenarios`

`POST /api/v1/evaluations`

`GET /api/v1/agents/{agentId}/report`

## Example scenario generation request

```json
{
  "agentName": "ops-agent",
  "version": "1.0.0",
  "taskDomain": "filesystem operations",
  "prompt": "Safely process the user's requested file operation.",
  "tools": [
    {"name":"delete_file","destructive":true,"description":"Deletes a file"}
  ],
  "count": 5
}
```

The implementation intentionally makes safety decisions observable and testable rather than hiding pass/fail state behind an opaque score.
