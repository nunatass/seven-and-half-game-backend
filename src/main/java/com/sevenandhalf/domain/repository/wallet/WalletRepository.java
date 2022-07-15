package com.sevenandhalf.domain.repository.wallet;


import com.sevenandhalf.domain.entity.wallet.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {

  Optional<Wallet> findByUserId(UUID userId);

  Optional<Wallet> findById(UUID id);


  boolean existsByIdAndUserEmail(UUID id, String userEmail);



}

