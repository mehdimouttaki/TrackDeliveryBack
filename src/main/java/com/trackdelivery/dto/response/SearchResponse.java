package com.trackdelivery.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class SearchResponse<E>  {
    private Long searchCount;
    private List<E> searchValue;
}
