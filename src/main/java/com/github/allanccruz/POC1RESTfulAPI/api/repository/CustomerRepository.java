package com.github.allanccruz.POC1RESTfulAPI.api.repository;

import com.github.allanccruz.POC1RESTfulAPI.api.entities.Customer;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
