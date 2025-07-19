package com.trackdelivery.serviceImpl;

import com.trackdelivery.dto.request.OrderRequest;
import com.trackdelivery.dto.request.SearchOrderRequestValues;
import com.trackdelivery.dto.request.SearchRequest;
import com.trackdelivery.dto.response.OrderResponse;
import com.trackdelivery.dto.response.SearchResponse;
import com.trackdelivery.entity.Client;
import com.trackdelivery.entity.Orders;
import com.trackdelivery.enums.State;
import com.trackdelivery.exception.OrderModificationException;
import com.trackdelivery.mapper.OrderResponseMapper;
import com.trackdelivery.repository.ClientRepository;
import com.trackdelivery.repository.OrderRepository;
import com.trackdelivery.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.trackdelivery.config.JwtSecurityUtils;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final OrderResponseMapper orderResponseMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ClientRepository clientRepository, OrderResponseMapper orderResponseMapper) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.orderResponseMapper = orderResponseMapper;
    }

    @Override
    public SearchResponse<OrderResponse> findAll(Integer pageNum, Integer pageSize, String sortBy, Integer sortOrder, Boolean isPageable, List<SearchRequest> searchRequests) {
        String username = JwtSecurityUtils.getAuthenticatedUsername();
        Client client = clientRepository.findByEmail(username).orElseThrow();

        SearchOrderRequestValues searchOrderRequestValues = new SearchOrderRequestValues();

        this.getSearchOrderRequestValues(searchRequests, searchOrderRequestValues);

        Sort sort = sortOrder != null && sortOrder == -1
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        PageRequest paging = PageRequest.of(pageNum, pageSize, sort);

        List<OrderResponse> responses = orderRepository.getOrdersByClientId(
                client.getId(), searchOrderRequestValues.getOrderNumber(),
                searchOrderRequestValues.getStateOrder(), paging
        );

        Long count = orderRepository.getOrdersByClientIdCount(
                client.getId(), searchOrderRequestValues.getOrderNumber(),
                searchOrderRequestValues.getStateOrder()
        );

        SearchResponse<OrderResponse> response = new SearchResponse<>();
        response.setSearchValue(responses);
        response.setSearchCount(count);
        return response;
    }

    @Override
    public ResponseEntity<OrderResponse> update(Long orderId, OrderRequest orderRequest) {
        Orders orders = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + orderId));

        if (orders.getState().equals(State.READY)) {
            throw new OrderModificationException("You can't change an order that is READY");
        }

        orders.setAddress(orderRequest.getAddress());
        orders.setDateOrder(orderRequest.getDateOrder());
        orderRepository.save(orders);
        OrderResponse response = orderResponseMapper.toResponse(orders);
        return ResponseEntity.ok(response);
    }



    private void getSearchOrderRequestValues(List<SearchRequest> searchRequestList, SearchOrderRequestValues searchOrderRequestValues) {


        for (SearchRequest request : searchRequestList) {
            if (request.getField().equals("client")) {
                searchOrderRequestValues.setClientId(Long.valueOf(request.getValue()));
            } else if (request.getField().equals("orderNumber")) {
                searchOrderRequestValues.setOrderNumber(String.valueOf(request.getValue()));
            }
            if (request.getField().equals("stateOrder")) {
                searchOrderRequestValues.setStateOrder(String.valueOf(request.getValue()));
            }
        }
    }
}
