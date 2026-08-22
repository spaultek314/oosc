package com.oosc.reliability.domain;

public enum FailureMode {
    PASS,
    TOOL_CALL_LOOP,
    HALLUCINATED_CONFIDENCE,
    DESTRUCTIVE_ACTION,
    GOAL_DRIFT,
    TIMEOUT,
    SANDBOX_ERROR
}
