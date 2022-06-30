package com.sevenandhalf.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
  @Id
  @GeneratedValue
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Column(name = "id", nullable = false)
  private UUID id;



  @Column(name = "full_name", nullable = false, length = 100)
  private String fullName;

  @Column(name = "email", nullable = false, unique = true, length = 45)
  private String email;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "password", nullable = false, length = 64)
  private String password;

}
