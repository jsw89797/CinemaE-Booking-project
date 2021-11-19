package com.andreasmarsh.SpringTest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MovieShowingRepository extends JpaRepository<MovieShowing, Long> {

    @Query("SELECT u FROM MovieShowing u WHERE u.movie = ?1")
    public Iterable<? extends MovieShowing> findByMovie(Movie movie);

    @Query("SELECT u FROM MovieShowing u WHERE u.movie = ?1")
    public MovieShowing getByMovie(Movie movie);
}
