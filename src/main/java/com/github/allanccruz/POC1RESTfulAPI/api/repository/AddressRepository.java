package com.github.allanccruz.POC1RESTfulAPI.api.repository;

import com.github.allanccruz.POC1RESTfulAPI.api.entities.Address;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository <Address, UUID> {
}
