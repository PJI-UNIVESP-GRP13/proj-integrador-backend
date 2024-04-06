package com.br.apicerquilhotodos.repository;

import com.br.apicerquilhotodos.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {
}
