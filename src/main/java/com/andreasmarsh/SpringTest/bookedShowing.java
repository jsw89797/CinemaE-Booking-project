package com.andreasmarsh.SpringTest;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bookedShowing")
public class bookedShowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookedShowingID;

    @ManyToOne
    @JoinColumn(name = "bookingID")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "movieID")
    private Movie movie;

    @Temporal(TemporalType.DATE)
    private java.util.Date date;

    @Temporal(TemporalType.TIME)
    private java.util.Date time;

    public bookedShowing() {

    }


    public bookedShowing(Booking booking, Movie movieID, Date date, Date time) {
        this.booking = booking;
        this.movie = movieID;
        this.date = date;
        this.time = time;
    }

    public Long getBookedShowingID() {
        return bookedShowingID;
    }

    public void setBookedShowingID(Long bookedShowingID) {
        this.bookedShowingID = bookedShowingID;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
