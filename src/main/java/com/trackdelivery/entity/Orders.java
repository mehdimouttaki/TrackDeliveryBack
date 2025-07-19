package com.trackdelivery.entity;

import com.trackdelivery.enums.Role;
import com.trackdelivery.enums.State;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Orders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String source;

    private String address;
    
    private LocalDateTime dateOrder;

    @Enumerated(EnumType.STRING)
    private State state;

    private String orderNumber;

    private String orderNumberTemporary;

    @ManyToOne
    private Client client;

    private Boolean canceled;

    private Boolean active;


}
