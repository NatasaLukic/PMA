package com.example.findacar.utils;

import com.example.findacar.model.Reservation;

import java.util.List;

public interface IReservationsHelper {
    List<Reservation> getPrevious();
    List<Reservation> getCurrent();
}
