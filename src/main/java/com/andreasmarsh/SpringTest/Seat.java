package com.andreasmarsh.SpringTest;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "booked_seats")
public class Seat {

    @Id
    private Long showID;

    @Column(name = "seatID")
    private Long seatID;

    @Column(name = "reserved")
    private boolean reserved;

    public Long getSeatId() {
        return seatID;
    }


    public Seat(){
        showID=0L;
        seatID=0L;
        reserved=false;
    }

    public boolean isReserved(){
        if(this.showID == showID && reserved){
            return true;
        } else{
            return false;
        }
    }

    public boolean getReserved(){
        return reserved;
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




}
