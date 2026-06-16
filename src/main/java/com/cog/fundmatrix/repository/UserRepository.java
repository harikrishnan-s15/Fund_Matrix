package com.cog.fundmatrix.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cog.fundmatrix.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

}
