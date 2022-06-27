package br.com.itau.animals.controller;

import br.com.itau.animals.api.controller.CatController;
import br.com.itau.animals.domain.BaseResponse;
import br.com.itau.animals.domain.response.CatResponse;
import br.com.itau.animals.services.CatService;
import br.com.itau.animals.util.DtoTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CatControllerTest {

    @Mock
    private CatService service;

    @InjectMocks
    private CatController controller;

    @Test
    @DisplayName("Test controller to update database")
    public void testUpdateDatabase() {
        doNothing().when(service).updateDatabaseCat();

        var response = controller.updateDatabase();

        verify(service, timeout(10)).updateDatabaseCat();
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Test controller to get all cats")
    public void testGetAll() {
        when(service.getAllCatsService(anyInt(), anyInt())).thenReturn(BaseResponse.<List<CatResponse>>builder()
                .data(Arrays.asList(DtoTest.getCatResponse()))
                .pagination(DtoTest.getPageResponse())
                .build());

        var response = controller.getAll(10, 0);

        assertNotNull(response);
        assertTrue(response.getBody().getData().size() > 0);
        assertTrue(response.getBody().getData().get(0).getOrigin().equals("Egypt"));
    }

    public void testGetId() {

    }
}