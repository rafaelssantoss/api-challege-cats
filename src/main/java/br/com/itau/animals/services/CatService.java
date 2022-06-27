package br.com.itau.animals.services;

import br.com.itau.animals.domain.BaseResponse;
import br.com.itau.animals.domain.response.CatResponse;

import java.util.List;

public interface CatService {

    void updateDatabaseCat();

    BaseResponse<List<CatResponse>> getAllCatsService(Integer size, Integer page);

    BaseResponse<CatResponse> getIdCatService(String idCat);

    BaseResponse<List<CatResponse>> getTemperamentCatService(Integer size, Integer page, String value);

    BaseResponse<List<CatResponse>> getOriginCatService(Integer size, Integer page, String value);
}