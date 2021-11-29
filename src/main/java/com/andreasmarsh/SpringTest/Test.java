package com.andreasmarsh.SpringTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;




@Controller
public class Test {
    @Autowired
    SeatRepository seatRepo;

    Test(){

    }




}
