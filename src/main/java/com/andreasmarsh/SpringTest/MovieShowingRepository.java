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
                    "insert into booked_seats (showid, seatid) values (:showID, 1), (:showID, 2), (:showID, 3), (:showID, 4), (:showID, 5), " +
                            "(:showID, 6), (:showID, 7), (:showID, 8), (:showID, 9), (:showID, 10), (:showID, 11), (:showID, 12), (:showID, 13), " +
                            "(:showID, 14), (:showID, 15), (:showID, 16), (:showID, 17), (:showID, 18), (:showID, 19), (:showID, 20), (:showID, 21), " +
                            "(:showID, 22), (:showID, 23), (:showID, 24), (:showID, 25), (:showID, 26), (:showID, 27), (:showID, 28), (:showID, 29), " +
                            "(:showID, 30), (:showID, 31), (:showID, 32), (:showID, 33), (:showID, 34), (:showID, 35), (:showID, 36), (:showID, 37), " +
                            "(:showID, 38), (:showID, 39), (:showID, 40)",
            nativeQuery = true)
    void fillTheater(@Param("showID") Long showID);

}
