package com.andreasmarsh.SpringTest;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;


    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "user_userID", nullable = true, referencedColumnName = "userID")
    @NotFound(action = NotFoundAction.IGNORE)
    private User user;


    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "booking_bookingID", nullable = true, referencedColumnName = "bookingID")
    @NotFound(action = NotFoundAction.IGNORE)
    private Booking booking;

    public Order() {

    }

    public Order(User user, Booking booking) {
        this.user = user;
        this.booking = booking;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Booking getBooking() {
        return this.booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}

