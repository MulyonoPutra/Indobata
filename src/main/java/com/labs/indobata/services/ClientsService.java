package com.labs.indobata.services;

import com.labs.indobata.domain.dto.ClientsDTO;
import com.labs.indobata.domain.entities.Clients;
import java.util.List;
import java.util.Optional;

public interface ClientsService {
  /**
   * Save a clients.
   * @param clientsDTO the entity to save.
   * @return the persisted entity.
   */
  ClientsDTO save(ClientsDTO clientsDTO);

  /**
   * Partially updates a clients.
   * @param clientsDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<ClientsDTO> partialUpdate(ClientsDTO clientsDTO);

  /**
   * Get all the categories.
   * @return the list of entities.
   */
  List<Clients> findAll();

  /**
   * Get the "id" clients.
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<ClientsDTO> findOne(Long id);

  /**
   * Delete the "id" clients.
   * @param id the id of the entity.
   */
  void delete(Long id);
}
