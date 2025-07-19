package com.trackdelivery.mapper;

import com.trackdelivery.dto.response.ClientBasicDto;
import com.trackdelivery.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientBasicAllMapper {
    ClientBasicDto toClientBasicAll(Client client);
}
