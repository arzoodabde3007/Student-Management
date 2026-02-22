package com.example.springproject.studentmanagement.repository;

import com.example.springproject.studentmanagement.Entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
