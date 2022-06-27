package br.com.itau.animals.util;

import br.com.itau.animals.domain.PageResponse;
import br.com.itau.animals.domain.response.CatResponse;
import br.com.itau.animals.repository.entity.AnimalPhoto;
import br.com.itau.animals.repository.entity.Cat;
import br.com.itau.animals.repository.entity.CatPhoto;

import java.time.LocalDateTime;

public class DtoTest {

    public static CatResponse getCatResponse() {
        return CatResponse.builder()
                .id("adsa")
                .breedName("Abyssinian")
                .origin("Egypt")
                .temperament("Active, Energetic, Independent, Intelligent, Gentle")
                .description("The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.")
                .build();
    }

    public static Cat getCat() {
        return Cat.builder()
                .id(1L)
                .catId("adsa")
                .origin("Egypt")
                .temperament("Active, Energetic, Independent, Intelligent, Gentle")
                .description("The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.")
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static CatPhoto getCatPhoto() {
        return CatPhoto.builder()
                .id(1L)
                .photo(AnimalPhoto.builder()
                        .id(1L)
                        .idPhoto("0XYvRd7oD")
                        .width(1204)
                        .height(1445)
                        .url("https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg")
                        .build())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static PageResponse getPageResponse() {
        return PageResponse.builder()
                .page(1)
                .numberOfElements(1)
                .totalPage(1)
                .totalNumber(1L)
                .build();
    }
}