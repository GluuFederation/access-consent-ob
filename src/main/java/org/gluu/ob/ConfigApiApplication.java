package org.gluu.ob;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.plugins.server.servlet.ResteasyContextParameters;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;

@ApplicationScoped
@Slf4j
public class ConfigApiApplication {

    void onStart(@Observes StartupEvent ev) {
        log.info("=================================================================");
        log.info("=============  STARTING API APPLICATION  ========================");
        log.info("=================================================================");
        System.setProperty(ResteasyContextParameters.RESTEASY_PATCH_FILTER_DISABLED, "true");
        log.info("=================================================================");
        log.info("==============  APPLICATION IS UP AND RUNNING ===================");
        log.info("=================================================================");
    }

    void onStop(@Observes ShutdownEvent ev) {
        log.info("================================================================");
        log.info("===========  API APPLICATION STOPPED  ==========================");
        log.info("================================================================");
    }

}
