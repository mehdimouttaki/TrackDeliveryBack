package com.trackdelivery.dto.response;

import com.trackdelivery.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;

    private String description;

    private String source;

    private String address;

    private LocalDateTime dateOrder;

    private State state;

    private String orderNumber;

    private String orderNumberTemporary;

    private ClientBasicDto client;

    private Boolean canceled;

    private Boolean active;

    public OrderResponse(Long id, String description, String source, String address, LocalDateTime dateOrder, State state, String orderNumber, String orderNumberTemporary, Long clientId,String clientFirstName,String clientLastName,Boolean clientActive, Boolean canceled, Boolean active) {
        this.id = id;
        this.description = description;
        this.source = source;
        this.address = address;
        this.dateOrder = dateOrder;
        this.state = state;
        this.orderNumber = orderNumber;
        this.orderNumberTemporary = orderNumberTemporary;
        this.client = new ClientBasicDto(clientId,clientFirstName,clientLastName,clientActive);
        this.canceled = canceled;
        this.active = active;
    }
}
