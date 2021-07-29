package com.labs.indobata.controllers;

import static com.labs.indobata.constants.ResponseConstants.DELETED_SUCCESSFULLY;

import com.labs.indobata.domain.dto.ClientsDTO;
import com.labs.indobata.domain.dto.ResponseMessages;
import com.labs.indobata.domain.entities.Clients;
import com.labs.indobata.exceptions.BadException;
import com.labs.indobata.repositories.ClientsRepository;
import com.labs.indobata.services.ClientsService;
import com.labs.indobata.utils.HeaderUtil;
import com.labs.indobata.utils.ResponseUtils;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientsController {

  private static final String ENTITY_NAME = "clients";

  private final Logger log = LoggerFactory.getLogger(ClientsController.class);

  private final ClientsService clientsService;

  private final ClientsRepository clientsRepository;

  public ClientsController(
    ClientsService clientsService,
    ClientsRepository clientsRepository
  ) {
    this.clientsService = clientsService;
    this.clientsRepository = clientsRepository;
  }

  /**
   * {@code Clients /clients} : Create a new clients.
   *
   * @param clientsDTO the clientsDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
   *         body the new cardDTO, or with status {@code 400 (Bad Request)} if the
   *         post has already an ID.
   * @throws BadException if the Location URI syntax is incorrect.
   */
  @PostMapping
  public ResponseEntity<ClientsDTO> create(
    @Valid @RequestBody ClientsDTO clientsDTO
  )
    throws BadException {
    log.debug("REST request to save Card : {}", clientsDTO);
    if (clientsDTO.getId() != null) {
      throw new BadException("Error!");
    }
    ClientsDTO result = clientsService.save(clientsDTO);
    return ResponseEntity.ok(result);
  }

  /**
   * {@code GET /clients} : get all the clients.
   *
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
   *         of cards in body.
   */
  @GetMapping
  public ResponseEntity<List<Clients>> findAllClients() {
    List<Clients> clients = clientsService.findAll();
    return new ResponseEntity<>(clients, HttpStatus.OK);
  }

  /**
   * {@code GET /clients/:id} : get the "id" clients.
   *
   * @param id the id of the postDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the postDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/{id}")
  public ResponseEntity<ClientsDTO> findById(@PathVariable Long id) {
    log.debug("REST request to get Card : {}", id);
    Optional<ClientsDTO> clients = clientsService.findOne(id);
    return new ResponseEntity(clients, HttpStatus.OK);
  }

  /**
   * {@code DELETE  /clients/:id} : delete the "id" clients.
   *
   * @param id the id of the clientsDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseMessages> deleteClients(@PathVariable Long id) {
    log.debug("REST request to delete Category : {}", id);
    clientsService.delete(id);
    return ResponseUtils.response(HttpStatus.OK, DELETED_SUCCESSFULLY);
  }

  /**
   * {@code PUT  /clients/:id} : Updates an existing clients.
   *
   * @param id the id of the clientsDTO to save.
   * @param clientsDTO the clientsDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clientsDTO,
   * or with status {@code 400 (Bad Request)} if the clientsDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the clientsDTO couldn't be updated.
   * @throws BadException if the Location URI syntax is incorrect.
   */
  @PutMapping("/{id}")
  public ResponseEntity<ClientsDTO> updateClients(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody ClientsDTO clientsDTO
  )
    throws BadException {
    log.debug("REST request to update Category : {}, {}", id, clientsDTO);
    if (clientsDTO.getId() == null) {
      throw new BadException("Invalid id");
    }
    if (!Objects.equals(id, clientsDTO.getId())) {
      throw new BadException("Invalid ID");
    }

    if (!clientsRepository.existsById(id)) {
      throw new BadException("Entity not found");
    }

    ClientsDTO result = clientsService.save(clientsDTO);
    return ResponseEntity
      .ok()
      .headers(
        HeaderUtil.createEntityUpdateAlert(
          ENTITY_NAME,
          clientsDTO.getId().toString()
        )
      )
      .body(result);
  }
}
