package com.sevenandhalf.domain.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sevenandhalf.domain.entity.wallet.Wallet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Column(name = "id", nullable = false)
  private UUID id;


  @NotNull(message = "Fullname must be provided")
  @Column(name = "full_name", nullable = false, length = 100)
  private String fullName;

  @NotNull(message = "Email must be provided")
  @Column(name = "email", nullable = false, unique = true, length = 45)
  private String email;

  @NotNull(message = "Username must be provided")
  @Column(name = "username", nullable = false)
  private String username;

  @NotNull(message = "Password must be provided")
  @Column(name = "password", nullable = false, length = 64)
  private String password;

  @NotNull(message = "Birth date must be provided")
  @Column(name = "birth_date", nullable = false)
  private Date birthday;


  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "wallet_id", referencedColumnName = "id")
  @JsonManagedReference
  private Wallet wallet;



}

