package org.gluu.ob.rest.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;

@Liveness
@ApplicationScoped
public class ApiHealthCheck implements HealthCheck {
    public HealthCheckResponse call() {
        return HealthCheckResponse.up("access-consent liveness");
    }
}
