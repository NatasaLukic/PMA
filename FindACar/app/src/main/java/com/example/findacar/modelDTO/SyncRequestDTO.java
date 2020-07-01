package com.example.findacar.modelDTO;

public class SyncRequestDTO {

    private long vehicleId;
    private long id;
    private long version;

    public SyncRequestDTO() {
    }

    public SyncRequestDTO(long vehicleId, long id, long version) {
        this.vehicleId = vehicleId;
        this.id = id;
        this.version = version;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
