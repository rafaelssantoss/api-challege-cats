package br.com.project.animals.services.translate;

import br.com.project.animals.util.DtoTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CatTranslateTest {

    @Test
    @DisplayName("Test with translate cat")
    public void testTranslate() {
        var catOne = DtoTest.getCat();
        catOne.setPhotos(Arrays.asList(DtoTest.getCatPhoto()));
        var catTwo = DtoTest.getCat();

        var responseOne = CatTranslate.translate(catOne);
        assertNotNull(responseOne);
        assertTrue(responseOne.getImage().equals("https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"));

        var responseTwo = CatTranslate.translate(catTwo);
        assertNotNull(responseTwo);
        assertFalse(responseTwo.getImage() != null);
    }
}