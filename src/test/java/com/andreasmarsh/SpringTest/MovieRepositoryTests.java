package com.andreasmarsh.SpringTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class MovieRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MovieRepository repo;

    @Autowired
    private CategoryRepository repo2;

    // test methods go below
    @Test
    public void testCreateUser() {
        Movie movie = new Movie();
        movie.setTitle("Shang-Chi");
        movie.setCast("Simu Liu, Awkwafina, Tony Chiu-Wai Leung");
        movie.setTrailer("https://www.youtube.com/watch?v=8YjFbMbfXaQ");
        movie.setDirector("Destin Daniel Cretton");
        movie.setProducer("Jonathan Schwartz");
        movie.setSynopsis("Shang-Chi and the Legend of the Ten Rings is a 2021 American superhero film based on Marvel Comics featuring the character Shang-Chi.");
        movie.setRating("PG-13");

        MovieShowing showing = new MovieShowing();
        Date date = new Date();
        showing.setMovie(movie);
        showing.setTheaterID(1L);
        showing.setDate(date);
        showing.setTime(date);

        List<MovieShowing> showings = new ArrayList<>();
        showings.add(showing);

        movie.setMovieShowings(showings);

        Review review = new Review();
        review.setMovie(movie);
        review.setReview("A good film. Would recommend!");
        review.setRating(95L);
        review.setReviewer("Andreas Marsh");

        List<Review> reviews = new ArrayList<>();
        reviews.add(review);

        movie.setReviews(reviews);

        Category category = new Category();
        category.setCategory("Action");

        repo2.save(category);

        Set<Category> categories = new HashSet<Category>();
        categories.add(category);

        movie.setCategories(categories);

        Movie savedMovie = repo.save(movie);

        Movie existMovie = entityManager.find(Movie.class, savedMovie.getMovieID());

        assertThat(movie.getTitle()).isEqualTo(existMovie.getTitle());

    }
}