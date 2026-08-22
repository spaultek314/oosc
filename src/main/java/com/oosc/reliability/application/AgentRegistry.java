package com.oosc.reliability.application;

import com.oosc.reliability.domain.Agent;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class AgentRegistry {
    private final ConcurrentHashMap<UUID, Agent> agents = new ConcurrentHashMap<>();

    public Agent register(Agent requested) {
        UUID id = requested.id() == null ? UUID.randomUUID() : requested.id();
        Agent agent = new Agent(id, requested.name(), requested.version(), requested.domain(), requested.systemPrompt(), requested.tools());
        agents.put(id, agent);
        return agent;
    }

    public Agent get(UUID id) {
        Agent agent = agents.get(id);
        if (agent == null) throw new IllegalArgumentException("agent not found: " + id);
        return agent;
    }
}
