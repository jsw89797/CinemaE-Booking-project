package com.andreasmarsh.SpringTest;

import javax.persistence.*;

@Entity
@Table(name = "movieCategory")
public class MovieCategory {

    @Id
    @ManyToOne
    @JoinColumn(name = "movieID")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "categoryID")
    private Category category;

    public MovieCategory() {

    }

    public MovieCategory(Movie movie, Category category) {
        this.movie = movie;
        this.category = category;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
