package com.javatechie.multiple.ds.api.role.repository;

import com.javatechie.multiple.ds.api.model.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    //queries
}
