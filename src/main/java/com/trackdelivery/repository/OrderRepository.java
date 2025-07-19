package com.trackdelivery.repository;


import com.trackdelivery.dto.response.OrderResponse;
import com.trackdelivery.entity.Orders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository  extends JpaRepository<Orders, Long>, JpaSpecificationExecutor<Orders> {


    @Query("select new com.trackdelivery.dto.response.OrderResponse(orders.id,orders.description,orders.source,orders.address,orders.dateOrder,orders.state,orders.orderNumber,orders.orderNumberTemporary,client.id,client.firstName,client.lastName,client.active,orders.canceled,orders.active) " +
            "from Orders orders  " +
            "left join Client client on client.id = orders.client.id " +
            "where (:clientId is null or client.id  =:clientId ) " +
            "and (:orderNumber is null or orders.orderNumber like :orderNumber% ) " +
            "order by orders.dateOrder ASC "
    )
    List<OrderResponse> getOrdersByClientId(@Param("clientId") Long clientId,
                                            @Param("orderNumber") String orderNumber,
                                            @Param("orderState") String  orderState,
                                            Pageable pageable);

    @Query("select count (orders.id)" +
            "from  Orders orders  " +
            "left join Client client on client.id = orders.client.id " +
            "where (:clientId is null or client.id  =:clientId ) " +
            "and (:orderNumber is null or orders.orderNumber like :orderNumber% ) "
    )
    Long getOrdersByClientIdCount(@Param("clientId") Long clientId,
                                            @Param("orderNumber") String orderNumber,
                                            @Param("orderState") String  orderState);
}
