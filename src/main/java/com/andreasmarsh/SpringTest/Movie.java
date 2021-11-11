package com.andreasmarsh.SpringTest;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieID;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "cast")
    private String cast;

    @Column(nullable = false, name = "director")
    private String director;

    @Column(nullable = false, name = "producer")
    private String producer;

    @Column(nullable = false, name = "synopsis", length = 8000)
    private String synopsis;

    @Column(nullable = false, name = "trailer", length = 800)
    private String trailer;

    @Column(nullable = false, name = "rating", length = 5)
    private String rating;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie", orphanRemoval = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie", orphanRemoval = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<MovieShowing> movieShowings = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie", orphanRemoval = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<MovieCategory> movieCategories = new ArrayList<>();

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
