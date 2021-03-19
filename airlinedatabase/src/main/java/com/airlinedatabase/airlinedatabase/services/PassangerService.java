package com.airlinedatabase.airlinedatabase.services;

import com.airlinedatabase.airlinedatabase.model.Passanger;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PassangerService {
    public abstract Page<Passanger> getAllPassangerPaged(int pageNum);
    public abstract List<Passanger> getAllPassangers();
    public abstract Optional<Passanger> getPassangerById(Long passangerId);
    public abstract Passanger savePassanger(Passanger passanger);
    public abstract void deletePassangerById(Long passangerId);

}
