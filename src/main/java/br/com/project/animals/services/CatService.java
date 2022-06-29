package br.com.project.animals.services;

import br.com.project.animals.domain.response.CatResponse;
import br.com.project.animals.domain.BaseResponse;

import java.util.List;

public interface CatService {

    void updateDatabaseCat();

    BaseResponse<List<CatResponse>> getAllCatsService(Integer size, Integer page);

    BaseResponse<CatResponse> getIdCatService(String idCat);

    BaseResponse<List<CatResponse>> getTemperamentCatService(Integer size, Integer page, String value);

    BaseResponse<List<CatResponse>> getOriginCatService(Integer size, Integer page, String value);
}