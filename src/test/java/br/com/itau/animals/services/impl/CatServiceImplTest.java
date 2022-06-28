package br.com.itau.animals.services.impl;

import br.com.itau.animals.exception.CatException;
import br.com.itau.animals.repository.CatRepository;
import br.com.itau.animals.services.impl.CatServiceImpl;
import br.com.itau.animals.services.webclient.CatWebClient;
import br.com.itau.animals.util.DtoTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CatServiceImplTest {

    @Mock
    private CatWebClient catWebClient;

    @Mock
    private CatRepository repository;

    @InjectMocks
    private CatServiceImpl service;

    @Test
    @DisplayName("Test save infos API Cat for database")
    public void testUpdateDatabase() {
        var cat1 = DtoTest.getBreedModel();
        var cat2 = DtoTest.getBreedModel();
        cat1.setId("y2y2");
        cat2.setImage(DtoTest.getImageModel());

        when(catWebClient.getAllBreedCat(isNull(), isNull()))
                .thenReturn(Arrays.asList(cat1, cat2));
        when(repository.findAll()).thenReturn(Arrays.asList());
        var entityCat1 = DtoTest.getCat();
        var entityCat2 = DtoTest.getCat();
        entityCat1.setCatId("y2y2");
        when(repository.saveAll(any())).thenReturn(Arrays.asList(entityCat1, entityCat2));

        service.updateDatabaseCat();

        //In case you don't have any cat to be registered
        when(repository.findAll()).thenReturn(Arrays.asList(entityCat1, entityCat2));

        service.updateDatabaseCat();
    }

    @Test
    @DisplayName("Test service to get all cats")
    public void testGetAllCatsService() {
        when(repository.findAll()).thenReturn(Arrays.asList(DtoTest.getCat()));
        when(repository.findAll(any(PageRequest.class))).thenReturn(DtoTest.getCatPage());

        var responseOne = service.getAllCatsService(null, null);
        assertNotNull(responseOne);
        assertTrue(responseOne.getData().size() > 0);

        var responseTwo = service.getAllCatsService(10, 0);
        assertNotNull(responseTwo);
        assertTrue(responseTwo.getData().size() > 0);
    }

    @Test
    @DisplayName("Test service to get id cat")
    public void testGetIdCatService() {
        when(repository.findByCatId(anyString())).thenReturn(DtoTest.getCat());

        var response = service.getIdCatService("adsa");
        assertNotNull(response);
        assertEquals("adsa", response.getData().getId());
        assertTrue(response.getData().getTemperament().contains("Intelligent"));
    }

    @Test
    @DisplayName("Test service error not found to get id cat")
    public void testGetIdCatServiceNotFound() {
        when(repository.findByCatId(anyString())).thenReturn(null);

        assertThrows(CatException.NotFound.class, () -> service.getIdCatService("adsa"));
    }

    @Test
    @DisplayName("Test service to get temperament cat")
    public void testGetTemperamentCatService() {
        when(repository.findByTemperament(anyString())).thenReturn(Arrays.asList(DtoTest.getCat()));
        when(repository.findByTemperament(anyString(), any(PageRequest.class))).thenReturn(DtoTest.getCatPage());

        var responseOne = service.getTemperamentCatService(null, null, "Intelligent");
        assertNotNull(responseOne);
        assertTrue(responseOne.getData().size() > 0);

        var responseTwo = service.getTemperamentCatService(10, 0, "Intelligent");
        assertNotNull(responseTwo);
        assertTrue(responseTwo.getData().size() > 0);
    }

    @Test
    @DisplayName("Test service to get origin cat")
    public void testGetOriginCatService() {
        when(repository.findByOrigin(anyString())).thenReturn(Arrays.asList(DtoTest.getCat()));
        when(repository.findByOrigin(anyString(), any(PageRequest.class))).thenReturn(DtoTest.getCatPage());

        var responseOne = service.getOriginCatService(null, null, "Egypt");
        assertNotNull(responseOne);
        assertTrue(responseOne.getData().size() > 0);

        var responseTwo = service.getOriginCatService(10, 0, "Egypt");
        assertNotNull(responseTwo);
        assertTrue(responseTwo.getData().size() > 0);
    }

    @Test
    @DisplayName("Test service error in request param 'value' get temperament and origin")
    public void testValueText() {
        assertThrows(CatException.BadRequest.class, () -> service.getTemperamentCatService(null, null, "1254"));
        assertThrows(CatException.BadRequest.class, () -> service.getOriginCatService(10, 0, "Temp12"));
    }
}