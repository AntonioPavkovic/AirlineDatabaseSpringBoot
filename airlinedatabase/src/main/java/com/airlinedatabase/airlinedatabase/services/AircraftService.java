package com.airlinedatabase.airlinedatabase.services;

import com.airlinedatabase.airlinedatabase.model.Aircraft;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AircraftService {
    public abstract Page<Aircraft> getAllAircraftsPaged(int pageNum);
    public abstract List<Aircraft> getAllAirpcrafts();
    public abstract Aircraft getAircraftById(Long aircraftId);
    public abstract Aircraft saveAircraft(Aircraft aircraft);
    public abstract void deleteAircraft(Long aircraftId);
}
