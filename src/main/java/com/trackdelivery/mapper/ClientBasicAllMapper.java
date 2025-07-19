package com.trackdelivery.mapper;

import com.trackdelivery.dto.response.ClientBasicAll;
import com.trackdelivery.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientBasicAllMapper {
    ClientBasicAll toClientBasicAll(Client client);
}
