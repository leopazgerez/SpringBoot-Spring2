package com.example.library.services;

import com.example.library.models.Location;

public interface LocationService {
    void createLocation();

    Location getLocation();

    Location updateLocation();

    void deleteLocation();
}
