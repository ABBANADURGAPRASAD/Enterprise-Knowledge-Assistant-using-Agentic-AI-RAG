package com.enterprise.eka.service.tools;

import java.util.function.Function;

/**
 * Contract for AI-invokable tools that agents can call via Spring AI function calling.
 */
public interface AgentTool<T, R> {
    String getName();
    String getDescription();
    Function<T, R> getFunction();
}
