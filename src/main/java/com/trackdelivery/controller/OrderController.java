package com.trackdelivery.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.trackdelivery.dto.request.OrderRequest;
import com.trackdelivery.dto.request.SearchRequest;
import com.trackdelivery.dto.response.OrderResponse;
import com.trackdelivery.dto.response.SearchResponse;
import com.trackdelivery.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/filter/table/all")
    public ResponseEntity<SearchResponse<OrderResponse>> getUserResponse(@RequestParam(defaultValue = "0") Integer pageNum,
                                                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                                                           @RequestParam(defaultValue = "id") String sortBy,
                                                                           @RequestParam(defaultValue = "1") Integer sortOrder,
                                                                           @RequestParam(defaultValue = "false") Boolean isPageable,
                                                                           @RequestBody List<SearchRequest> searchRequests) throws IllegalAccessException, JsonProcessingException {

        SearchResponse<OrderResponse> searchResponse = orderService.findAll(pageNum, pageSize, sortBy, sortOrder, isPageable, searchRequests);
        return ResponseEntity.ok(searchResponse);

    }


    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponse> update(@PathVariable() Long orderId, @Validated @RequestBody OrderRequest orderRequest) throws Exception {
        return orderService.update(orderId, orderRequest);
    }

}
