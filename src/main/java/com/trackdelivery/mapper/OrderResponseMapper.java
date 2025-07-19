package com.trackdelivery.mapper;

import com.trackdelivery.dto.response.OrderResponse;
import com.trackdelivery.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ClientBasicAllMapper.class)
public interface OrderResponseMapper {
    OrderResponse toResponse(Orders orders);
}
