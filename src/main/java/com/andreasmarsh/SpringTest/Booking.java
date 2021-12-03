package com.andreasmarsh.SpringTest;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.*;

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

    //needs to hold the ticketID
    @Column(name = "ticketID")
    private Long ticketID;

    @Column(name = "movie_title")
    private String movieTitle;

    @Temporal(TemporalType.DATE)
    private java.util.Date date;

    @Temporal(TemporalType.TIME)
    private java.util.Date time;

    @Column(name = "cardCVV")
    private String cardCVV;

    @Column(name = "cardType")
    private String cardType;

    @Column(name = "cardNumber", length = 16)
    private Long cardNumber;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    @Column(name="price")
    private Double price;

    @Column(name="promotionCode")
    private String promoCode;

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

    public Long getTicketID() {
        return this.ticketID;
    }

    public void setTicketID(Long ticketID) {
        this.ticketID = ticketID;
    }

    public String getMovieTitle() { return this.movieTitle; }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
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

    public Long getCardNumber() { return this.cardNumber; }

    public void setCardNumber(Long cardNumber) { this.cardNumber = cardNumber;}

    public String getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(String cvv) {
        this.cardCVV = cvv;
    }

    public String getCardType () { return this.cardType;}

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}


