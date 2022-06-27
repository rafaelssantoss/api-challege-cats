package br.com.itau.animals.services.impl;

import br.com.itau.animals.domain.BaseResponse;
import br.com.itau.animals.domain.PageResponse;
import br.com.itau.animals.domain.response.CatResponse;
import br.com.itau.animals.exception.CatException;
import br.com.itau.animals.repository.CatRepository;
import br.com.itau.animals.repository.entity.AnimalPhoto;
import br.com.itau.animals.repository.entity.Cat;
import br.com.itau.animals.repository.entity.CatPhoto;
import br.com.itau.animals.services.CatService;
import br.com.itau.animals.services.translate.CatTranslate;
import br.com.itau.animals.services.webclient.CatWebClient;
import br.com.itau.animals.services.webclient.model.BreedModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatWebClient catWebClient;
    private final CatRepository repository;

    @Transactional
    @Override
    public void updateDatabaseCat() {
        log.info("Starting request and saving the cats in database");
        var responseEndpoint = catWebClient.getAllBreedCat(null, null);
        var entityCats= repository.findAll();
        List<Cat> cats = new ArrayList();

        responseEndpoint.forEach(cat -> {
            Boolean exist = false;
            for (Cat entity : entityCats) {
                if (cat.getId().equals(entity.getCatId())) {
                    log.info("Cat id '{}' already exists in database", cat.getId());
                    exist = true;
                }
            }
            if (!exist)
                cats.add(creatingCatEntity(cat));
        });

        if (cats.size() > 0) {
            repository.saveAll(cats);
            log.info("Cats saved with success. Total {} saved", cats.size());
        } else log.info("Not found anything to new cats saving in the database");
    }

    @Override
    public BaseResponse<List<CatResponse>> getAllCatsService(Integer size, Integer page) {
        log.info("Starting request GET to all breed cats");

        if (size != null && page != null) {
            var cats = repository.findAll(PageRequest.of(page, size));
            return getResponse(cats, null);
        } else {
            var cats = repository.findAll();
            return getResponse(null, cats);
        }
    }

    @Override
    public BaseResponse<CatResponse> getIdCatService(String idCat) {
        log.info("Starting request GET/{} to all breed cat", idCat);

        var cat = repository.findByCatId(idCat);
        if (cat == null) {
            log.error("Not found cat id '{}' in the database", idCat);
            throw new CatException.NotFound(String.format("Not found id '%s'", idCat));
        }
        var response = CatTranslate.translate(cat);

        log.info("Search id '{}' successfully executed", idCat);
        return BaseResponse.<CatResponse>builder()
                .data(response)
                .build();
    }

    @Override
    public BaseResponse<List<CatResponse>> getTemperamentCatService(Integer size, Integer page, String value) {
        log.info("Starting request GET to temperament {} cats", value);

        if (size != null && page != null) {
            var cats = repository.findByTemperament(temperamentFilter(value), PageRequest.of(page, size));
            return getResponse(cats, null);
        } else {
            var cats = repository.findByTemperament(temperamentFilter(value));
            return getResponse(null, cats);
        }
    }

    @Override
    public BaseResponse<List<CatResponse>> getOriginCatService(Integer size, Integer page, String value) {
        log.info("Starting request GET to origin {} cats", value);

        if (size != null && page != null) {
            var cats = repository.findByOrigin(temperamentFilter(value), PageRequest.of(page, size));
            return getResponse(cats, null);
        } else {
            var cats = repository.findByOrigin(temperamentFilter(value));
            return getResponse(null, cats);
        }
    }

    private BaseResponse<List<CatResponse>> getResponse(Page<Cat> catPage, List<Cat> catList) {
        if (catPage != null) {
            log.info("Request is paged");
            var response = catPage.get()
                    .map(cat -> CatTranslate.translate(cat))
                    .collect(Collectors.toList());

            log.info("Request successfully executed");
            return BaseResponse.<List<CatResponse>>builder()
                    .data(response)
                    .pagination(PageResponse.builder()
                            .page(catPage.getNumber())
                            .numberOfElements(catPage.getNumberOfElements())
                            .totalNumber(catPage.getTotalElements())
                            .totalPage(catPage.getTotalPages())
                            .build())
                    .build();
        } else {
            log.info("Request is not paged");
            var response = catList.stream()
                    .map(cat -> CatTranslate.translate(cat))
                    .collect(Collectors.toList());

            return BaseResponse.<List<CatResponse>>builder()
                    .data(response)
                    .build();
        }
    }

    private String temperamentFilter(String value) {
        log.info("Filtering value string");

        if (!value.matches("^[A-Za-z ]+$"))
            throw new CatException.BadRequest("Invalid temper");

        var array = value.split("\\s+");
        Arrays.stream(array).forEach(text -> text.substring(0, 1).toUpperCase().concat(text.substring(1).toLowerCase()));

        String result = "";
        for (String text : array) {
            result = result +
                    text + " ";
        }
        result = result.substring(0, result.length() -1);
        return result;
    }

    private Cat creatingCatEntity(BreedModel cat) {
        var catEntity = Cat.builder()
                .catId(cat.getId())
                .breed(cat.getName())
                .origin(cat.getOrigin())
                .temperament(cat.getTemperament())
                .description(cat.getDescription())
                .createdAt(LocalDateTime.now())
                .build();

        if (cat.getImage() != null && cat.getImage().getId() != null) {
            List<CatPhoto> photo = Arrays.asList(CatPhoto.builder()
                    .photo(AnimalPhoto.builder()
                            .idPhoto(cat.getImage().getId())
                            .width(cat.getImage().getWidth())
                            .height(cat.getImage().getHeight())
                            .url(cat.getImage().getUrl())
                            .createdAt(LocalDateTime.now())
                            .build())
                    .createdAt(LocalDateTime.now())
                    .build());
            photo.forEach(catPhoto -> catPhoto.setCat(catEntity));
            catEntity.setPhotos(photo);
        }
        log.info("Cat id '{}' added in the list to saving", cat.getId());
        return catEntity;
    }
}