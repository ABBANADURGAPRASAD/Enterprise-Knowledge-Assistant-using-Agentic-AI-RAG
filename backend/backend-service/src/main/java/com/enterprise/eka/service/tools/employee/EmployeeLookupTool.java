package com.enterprise.eka.service.tools.employee;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class EmployeeLookupTool {

    @JsonClassDescription("Look up employee information by employee code or name")
    public record Request(
            @JsonProperty(required = true)
            @JsonPropertyDescription("Employee code or partial name to search")
            String query
    ) {}

    public record Response(String employeeCode, String name, String department, String jobTitle, String status) {}

    @Bean
    @Description("Look up employee information by employee code or name")
    public Function<Request, Response> employeeLookup() {
        return request -> {
            log.info("Tool call: employeeLookup query={}", request.query());
            return new Response("EMP001", "John Doe", "Engineering", "Senior Developer", "ACTIVE");
        };
    }
}
