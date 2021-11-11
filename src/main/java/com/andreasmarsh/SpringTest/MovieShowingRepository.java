package com.andreasmarsh.SpringTest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieShowingRepository extends JpaRepository<MovieShowing, Long> {

}
