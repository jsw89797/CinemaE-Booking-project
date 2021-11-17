package com.andreasmarsh.SpringTest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "SELECT EXISTS(SELECT 1 FROM users WHERE movie = ?1)", nativeQuery=true)
    public BigInteger doesMovieExist(String movie);
}
