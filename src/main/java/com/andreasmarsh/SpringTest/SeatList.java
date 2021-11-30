package com.andreasmarsh.SpringTest;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seats")
public class SeatList {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ticketid")
    private Long ticketID;

    private Long seatID;

    private Long showID;

    private Long reserved;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seats", orphanRemoval = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Seat> seats = new ArrayList<>();



    public void addBookedSeats(Long seatID, Long showID, Long reserved){
        this.seats.add(new Seat(seatID, showID, reserved, this));
    }


}
