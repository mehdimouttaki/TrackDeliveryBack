package com.trackdelivery.service;

import com.trackdelivery.dto.request.OrderRequest;
import com.trackdelivery.dto.request.PageableReq;
import com.trackdelivery.dto.request.SearchRequest;
import com.trackdelivery.dto.response.OrderResponse;
import com.trackdelivery.dto.response.SearchResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    SearchResponse<OrderResponse> findAll(Integer pageNum, Integer pageSize, String sortBy, Integer sortOrder, Boolean isPageable, List<SearchRequest> searchRequests);

    ResponseEntity<OrderResponse> update(Long orderId, OrderRequest orderRequest) throws Exception;
}
