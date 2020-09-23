package org.gluu.ob;

import com.intuit.karate.junit5.Karate;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class KarateTestRunner {

    @Karate.Test
    Karate testFullPath() {
        //return Karate.run("classpath:karate/tags.feature").tags("@first");
        return Karate.run("src/test/resources/feature");
    }

}
