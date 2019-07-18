package com.codecool.web.service;

import com.codecool.web.model.Greeting;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GreetingServiceTest {

    @Test
    void testGetGreeting() {
        // given
        GreetingService service = new GreetingService();

        // when
        List<Greeting> greetings = service.getGreetings();

        // then
        assertNotNull(greetings);
        assertEquals(4, greetings.size());
    }
}
