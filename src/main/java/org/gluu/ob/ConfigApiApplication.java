package org.gluu.ob;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.jboss.resteasy.plugins.server.servlet.ResteasyContextParameters;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

@ApplicationScoped
public class ConfigApiApplication {

    @Inject
    Logger logger;

    @Inject
    BeanManager beanManager;

    void onStart(@Observes StartupEvent ev) {
        logger.info("=================================================================");
        logger.info("=============  STARTING API APPLICATION  ========================");
        logger.info("=================================================================");
        System.setProperty(ResteasyContextParameters.RESTEASY_PATCH_FILTER_DISABLED, "true");
        logger.info("=================================================================");
        logger.info("==============  APPLICATION IS UP AND RUNNING ===================");
        logger.info("=================================================================");
    }

    void onStop(@Observes ShutdownEvent ev) {
        logger.info("================================================================");
        logger.info("===========  API APPLICATION STOPPED  ==========================");
        logger.info("================================================================");
    }
}
