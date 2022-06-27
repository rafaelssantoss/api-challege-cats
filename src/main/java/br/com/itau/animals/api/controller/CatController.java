package br.com.itau.animals.api.controller;

import br.com.itau.animals.api.CatApi;
import br.com.itau.animals.domain.BaseResponse;
import br.com.itau.animals.domain.response.CatResponse;
import br.com.itau.animals.services.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CatController implements CatApi {

    private final CatService service;

    @Override
    public ResponseEntity updateDatabase() {
        service.updateDatabaseCat();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<BaseResponse<List<CatResponse>>> getAll(Integer size, Integer page) {
        return new ResponseEntity(service.getAllCatsService(size, page), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse<CatResponse>> getId(String idCat) {
        return new ResponseEntity(service.getIdCatService(idCat), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse<List<CatResponse>>> getTemperamentCat(Integer size, Integer page, String value) {
        return new ResponseEntity(service.getTemperamentCatService(size, page, value), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse<List<CatResponse>>> getOriginCat(Integer size, Integer page, String value) {
        return new ResponseEntity(service.getOriginCatService(size, page, value), HttpStatus.OK);
    }
}