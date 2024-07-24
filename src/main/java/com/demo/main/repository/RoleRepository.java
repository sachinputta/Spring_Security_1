package com.demo.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.main.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

}
