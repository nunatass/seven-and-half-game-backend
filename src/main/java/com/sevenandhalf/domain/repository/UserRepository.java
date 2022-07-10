package com.sevenandhalf.domain.repository;


import com.sevenandhalf.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

  User findByEmail(String email);

  List<User> findAll();
}
