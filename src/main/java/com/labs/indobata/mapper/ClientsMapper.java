package com.labs.indobata.mapper;

import com.labs.indobata.domain.dto.ClientsDTO;
import com.labs.indobata.domain.entities.Clients;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {})
public interface ClientsMapper extends EntityMapper<ClientsDTO, Clients> {
  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  ClientsDTO toDtoId(Clients clients);
}
