package com.labs.indobata.repositories;

import com.labs.indobata.domain.entities.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<Clients, Long> {
}
