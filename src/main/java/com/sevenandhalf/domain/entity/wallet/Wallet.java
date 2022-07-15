package com.sevenandhalf.domain.entity.wallet;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sevenandhalf.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wallets")
public class Wallet implements Serializable {

  private static final long serialVersionUID = -9018130641242873583L;

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Column(name = "id", nullable = false)
  private UUID id;


  @Min(0)
  @Column(name = "balance",nullable = false)
  @Digits(integer = 10, fraction = 5)
  @NotNull(message = "Wallet balance must be provided")
  private BigDecimal balance;


  @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
  @JsonBackReference
  private User user;


  @OneToMany(mappedBy = "wallet", fetch = FetchType.LAZY)
  @Transient
  @JsonManagedReference
  private List<Transaction> transactions;

}

