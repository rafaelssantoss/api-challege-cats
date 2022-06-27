package br.com.itau.animals.api;

import br.com.itau.animals.domain.BaseResponse;
import br.com.itau.animals.domain.response.CatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/cats")
public interface CatApi {

    @PutMapping
    ResponseEntity updateDatabase();

    @GetMapping
    ResponseEntity<BaseResponse<List<CatResponse>>> getAll(
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page);

    @GetMapping("/{id}")
    ResponseEntity<BaseResponse<CatResponse>> getId(@PathVariable("id") String idCat);

    @GetMapping("/temperament")
    ResponseEntity<BaseResponse<List<CatResponse>>> getTemperamentCat(
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam("value") String value);

    @GetMapping("/origin")
    ResponseEntity<BaseResponse<List<CatResponse>>> getOriginCat(
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam("value") String value);
}