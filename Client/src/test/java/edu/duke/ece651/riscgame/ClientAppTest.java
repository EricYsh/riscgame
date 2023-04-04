package edu.duke.ece651.riscgame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClientAppTest {
    @Test void appHasAGreeting() {
        ClientApp classUnderTest = new ClientApp();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
}
