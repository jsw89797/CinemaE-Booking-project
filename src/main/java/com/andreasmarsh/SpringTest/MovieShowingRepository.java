package com.andreasmarsh.SpringTest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MovieShowingRepository extends JpaRepository<MovieShowing, Long> {

    @Query("SELECT u FROM MovieShowing u WHERE u.movie = ?1")
    public Iterable<? extends MovieShowing> findByMovie(Movie movie);

    /**
    @Query("INSERT INTO booked_seats")
    public void fillTheater(Long id);
    */

    @Transactional
    @Modifying
    @Query(
            value =
                    "insert into booked_seats (showid, seatid, reserved) values (:showID, 1, 0), (:showID, 2, 0), (:showID, 3, 0), (:showID, 4, 0), (:showID, 5, 0), " +
                            "(:showID, 6, 0), (:showID, 7, 0), (:showID, 8, 0), (:showID, 9, 0), (:showID, 10, 0), (:showID, 11, 0), (:showID, 12, 0), (:showID, 13, 0), " +
                            "(:showID, 14, 0), (:showID, 15, 0), (:showID, 16, 0), (:showID, 17, 0), (:showID, 18, 0), (:showID, 19, 0), (:showID, 20, 0), (:showID, 21, 0), " +
                            "(:showID, 22, 0), (:showID, 23, 0), (:showID, 24, 0), (:showID, 25, 0), (:showID, 26, 0), (:showID, 27, 0), (:showID, 28, 0), (:showID, 29, 0), " +
                            "(:showID, 30, 0), (:showID, 31, 0), (:showID, 32, 0), (:showID, 33, 0), (:showID, 34, 0), (:showID, 35, 0), (:showID, 36, 0), (:showID, 37, 0), " +
                            "(:showID, 38, 0), (:showID, 39, 0), (:showID, 40, 0)",
            nativeQuery = true)
    void fillTheater(@Param("showID") Long showID);

}
