package com.sevenandhalf.domain.repository;


import com.sevenandhalf.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

  Optional<User> findByEmail(String email);

  List<User> findAll();

  boolean existsByIdAndEmail(UUID id, String email);
}
