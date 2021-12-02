package com.andreasmarsh.SpringTest;

import org.springframework.data.jpa.repository.Query;import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Long> {
    @Query("SELECT u FROM Price u WHERE u.ticketType = ?1")
    public Price findByTicketType(String ticketType);
}
