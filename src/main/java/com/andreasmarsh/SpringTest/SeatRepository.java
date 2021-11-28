package com.andreasmarsh.SpringTest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


    public interface SeatRepository extends JpaRepository<Seat, Long> {

        @Query("SELECT u FROM Seat u WHERE u.seatID = ?1")
        public Optional<Seat> findBySeatId(Long id);

        @Query("SELECT u FROM Seat u WHERE u.showID = ?1")
        public Optional<Seat> findByShowId(Long id);

        @Query("SELECT u FROM Seat u WHERE u.reserved = ?1")
        public List<Seat> findByReservations(Boolean reserved);


    }

