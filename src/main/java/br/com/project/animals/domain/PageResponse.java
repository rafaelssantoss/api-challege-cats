package br.com.project.animals.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PageResponse {

    @JsonProperty("number_of_elements")
    private int numberOfElements;

    private int page;

    @JsonProperty("total_pages")
    private int totalPage;

    @JsonProperty("total_number_of_elements")
    private Long totalNumber;
}
