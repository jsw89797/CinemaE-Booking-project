package com.andreasmarsh.SpringTest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

    @Repository
    public interface SeatListRepository extends JpaRepository<SeatList, Long> {

        @Query("SELECT u FROM SeatList u WHERE u.seatID = ?1")
        public Optional<SeatList> findBySeatID(Long seatID);

        @Query("SELECT u FROM SeatList u WHERE u.showID = ?1")
        public Optional<SeatList> findByShowId(Long id);


    }

