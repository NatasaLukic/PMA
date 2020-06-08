package com.example.findacar.adapters;

import java.text.ParseException;
import java.util.Date;

public interface CurrentReservationHelper {

    public boolean passed(Date pickUpDate, int cancel) throws ParseException;
    public String getCancelDate(Date pickUpDate, int cancel) throws ParseException;


}
