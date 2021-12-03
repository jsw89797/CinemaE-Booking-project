package com.andreasmarsh.SpringTest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    @Query("SELECT u FROM CreditCard u WHERE u.cardType = ?1")
    public CreditCard findByType(String search);
}