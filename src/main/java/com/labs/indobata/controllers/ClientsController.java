package com.labs.indobata.controllers;

import com.labs.indobata.domain.dto.ClientsDTO;
import com.labs.indobata.domain.entities.Clients;
import com.labs.indobata.exceptions.BadException;
import com.labs.indobata.services.ClientsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientsController {
    private final Logger log = LoggerFactory.getLogger(ClientsController.class);

    private final ClientsService clientsService;

    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
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
    public ResponseEntity<ClientsDTO> create(@Valid @RequestBody ClientsDTO clientsDTO) throws BadException {
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

}

