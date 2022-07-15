package com.sevenandhalf.domain.entity.wallet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
@EntityListeners(AuditingEntityListener.class)
public class Transaction {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Column(name = "id", nullable = false)
  private UUID id;

  @NotNull(message = "Transaction type must be provided")
  @Column(name = "type", nullable = false)
  @Enumerated(EnumType.STRING)
  private TransactionType type;

  @NotNull(message = "Transaction amount must be provided")
  @Column(name = "amount", nullable = false)
  @Digits(integer = 4, fraction = 3)
  private BigDecimal amount;

  @NotNull(message = "Transaction wallet must be provided")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "wallet_id")
  @JsonBackReference
  private Wallet wallet;

  @Column
  @Digits(integer = 4, fraction = 3)
  private BigDecimal balanceAfterTransaction;

  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

}