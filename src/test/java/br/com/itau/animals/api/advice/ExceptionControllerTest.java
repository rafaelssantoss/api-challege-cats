package br.com.itau.animals.api.advice;

import br.com.itau.animals.exception.CatException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionControllerTest {

    private ExceptionController controller = new ExceptionController();

    @Test
    @DisplayName("Test controller exception to not found")
    public void testControlerAdviceNotFound() {
        var response = controller.notFound(new CatException.NotFound("NOT FOUND"));

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().getMessage().equals("NOT FOUND"));
    }

    @Test
    @DisplayName("Test controller exception to bad request")
    public void testControlerAdviceBadRequest() {
        var response = controller.badRequest(new CatException.BadRequest("BAD REQUEST"));

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().getMessage().equals("BAD REQUEST"));
    }
}