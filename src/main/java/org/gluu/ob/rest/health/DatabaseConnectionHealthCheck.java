package org.gluu.ob.rest.health;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;

@Readiness
@ApplicationScoped
@Slf4j
public class DatabaseConnectionHealthCheck implements HealthCheck {

    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("access-consent-ob readiness");
        try {
            responseBuilder.up();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            responseBuilder.down().withData("error", e.getMessage());
        }
        return responseBuilder.build();
    }
}
