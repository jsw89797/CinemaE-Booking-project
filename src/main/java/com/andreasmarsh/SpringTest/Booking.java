package com.andreasmarsh.SpringTest;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingID;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie", orphanRemoval = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<bookedShowing> bookedShowings = new ArrayList<>();


    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "user_userID", nullable = true, referencedColumnName = "userID")
    @NotFound(action = NotFoundAction.IGNORE)
    private User user;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Long getBookingID() {
        return bookingID;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user){ this.user = user; }


    public void setMovieID(Long movieID) {
        this.bookingID = movieID;
    }

    public List<bookedShowing> getBookedShowings() {
        return bookedShowings;
    }

    public void setBookedShowings(List<bookedShowing> bookedShowings) {
        this.bookedShowings = bookedShowings;
    }

}
