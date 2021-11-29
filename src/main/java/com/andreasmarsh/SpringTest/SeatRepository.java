package com.andreasmarsh.SpringTest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query("SELECT u FROM Seat u WHERE u.seatID = ?1")
    public Seat findBySeatID(Long seatID);

    @Query("SELECT u FROM Seat u WHERE u.showID = ?1")
    public Seat findByShowId(Long id);
}