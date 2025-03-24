package com.inmediasp.todoList.unitTests.restController;

import com.inmediasp.todoList.restController.RedirectController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RedirectControllerTest {

    RedirectController redirectController = new RedirectController();

    @Test
    void WHEN_accessingAnyRessource_THEN_redirectToRoot() {
        String expectedResult = "forward:/";

        String result = redirectController.redirect();

        assertEquals(expectedResult, result);
    }
}