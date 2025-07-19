package com.trackdelivery.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchOrderRequestValues {
    private Long clientId;
    private String stateOrder;
    private String orderNumber;
}
