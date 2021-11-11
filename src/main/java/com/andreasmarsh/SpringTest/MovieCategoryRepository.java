package com.andreasmarsh.SpringTest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCategoryRepository extends JpaRepository<MovieCategory, Long> {
}
