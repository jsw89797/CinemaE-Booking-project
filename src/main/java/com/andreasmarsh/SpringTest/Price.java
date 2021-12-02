package com.andreasmarsh.SpringTest;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "Prices")
public class Price {

    @Id
    @Column(nullable = false, name = "ticketType")
    private String ticketType;

    @Column(nullable = false, name = "ticketPrice")
    private Double ticketPrice;

    @Column(nullable = false, name="bookingFee")
    private Double bookingFee;

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Double getBookingFee() {
        return bookingFee;
    }

    public void setBookingFee(Double bookingFee) {
        this.bookingFee = bookingFee;
    }



}
