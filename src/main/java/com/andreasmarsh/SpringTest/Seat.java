package com.andreasmarsh.SpringTest;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "booked_seats")
public class Seat {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long ticketID;

    private Long showID;

    @ManyToOne
    @JoinColumn(name = "seatListID")
    private SeatList seats;


    @Column(name = "seatID")
    private Long seatID;

    @Column(name = "reserved")
    private Long reserved;

    public Long getSeatId() {
        return seatID;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    public Seat(){
        showID=0L;
        seatID=0L;
        reserved=0L;
    }

    public boolean isReserved(){
        return UserServices.isReserved(seatID,showID);
    }

    public Long getReserved(){
        return reserved;
    }

    public void setReserved( Long reserved){
        this.reserved = reserved;
    }
    public void setSeatId(Long seatID) {
        this.seatID = seatID;
    }

    public Long getShowId() {
        return showID;
    }

    public void setShowId(Long showID) {
        this.showID = showID;
    }

    public Seat(Long seatID, Long showID, Long reserved, SeatList seats) {
        this.seatID = seatID;
        this.showID = showID;
        this.reserved= reserved;
        this.seats = seats;
    }


}
