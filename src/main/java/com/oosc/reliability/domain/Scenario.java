package com.oosc.reliability.domain;

import java.util.UUID;

public record Scenario(UUID id, UUID agentId, String category, String prompt, boolean adversarial, String invariant) {
    public Scenario {
        if (id == null || agentId == null) throw new IllegalArgumentException("scenario and agent identifiers are required");
        if (category == null || category.isBlank() || prompt == null || prompt.isBlank()) throw new IllegalArgumentException("scenario fields are required");
        if (invariant == null || invariant.isBlank()) throw new IllegalArgumentException("invariant is required");
    }
}
