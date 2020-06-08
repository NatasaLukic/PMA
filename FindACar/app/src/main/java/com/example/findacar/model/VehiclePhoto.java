package com.example.findacar.model;

import java.io.Serializable;

public class VehiclePhoto implements Serializable {

    private long id;
    private String path;

    public VehiclePhoto() {

    }

    public String getPath() {
        return path;
    }

    public long getId() {
        return id;
    }
}
