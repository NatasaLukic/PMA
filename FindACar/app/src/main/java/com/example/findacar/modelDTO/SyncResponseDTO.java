package com.example.findacar.modelDTO;

import com.example.findacar.model.Review;

import java.util.List;

public class SyncResponseDTO {

    private long vehicleId;
    private long newVersion;
    private List<Review> newReviews;

    public SyncResponseDTO() {
        super();

    }

    public SyncResponseDTO(long vehicleId, long newVersion, List<Review> newReviews) {
        this.vehicleId = vehicleId;
        this.newReviews = newReviews;
        this.newVersion = newVersion;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public List<Review> getNewReviews() {
        return newReviews;
    }

    public void setNewReviews(List<Review> newReviews) {
        this.newReviews = newReviews;
    }

    public long getNewVersion() {
        return newVersion;
    }

    public void setNewVersion(long newVersion) {
        this.newVersion = newVersion;
    }
}