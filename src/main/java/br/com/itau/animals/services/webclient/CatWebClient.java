package br.com.itau.animals.services.webclient;

import br.com.itau.animals.services.webclient.model.BreedModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
public class CatWebClient {

    @Value("${api.cat.key}")
    private String apiKey;

    @Qualifier("webclient_cat")
    @Autowired
    private WebClient webClient;

    public List<BreedModel> getAllBreedCat(Integer size, Integer page) {
        return webClient.get()
                .uri(uriBuilder -> size != null && page != null ? uriBuilder
                        .path("/v1/breeds")
                        .queryParam("limit", size)
                        .queryParam("page", page)
                        .build()
                        :
                        uriBuilder
                        .path("/v1/breeds")
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("x-api-key", apiKey)
                .retrieve()
                .bodyToFlux(BreedModel.class)
                .collectList()
                .onErrorResume(error -> {
                    log.error("Error Request API CATS -> {}", error.getMessage());
                    log.error("Stack: {}", error.getStackTrace());
                    return null;
                })
                .block();
    }
}