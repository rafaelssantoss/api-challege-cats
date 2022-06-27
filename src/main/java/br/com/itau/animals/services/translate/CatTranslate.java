package br.com.itau.animals.services.translate;

import br.com.itau.animals.domain.response.CatResponse;
import br.com.itau.animals.repository.entity.Cat;

public class CatTranslate {

    public static CatResponse translate(Cat cat) {
        return CatResponse.builder()
                .id(cat.getCatId())
                .breedName(cat.getBreed())
                .origin(cat.getOrigin())
                .temperament(cat.getTemperament())
                .description(cat.getDescription())
                .image(cat.getPhotos() != null && cat.getPhotos().size() > 0 ?
                        cat.getPhotos().get(0).getPhoto().getUrl() : null)
                .build();
    }
}