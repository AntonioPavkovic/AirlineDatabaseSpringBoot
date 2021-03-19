package com.airlinedatabase.airlinedatabase.services.servicesimpl;

import com.airlinedatabase.airlinedatabase.model.Passanger;
import com.airlinedatabase.airlinedatabase.reposetory.PassangerRepository;
import com.airlinedatabase.airlinedatabase.services.PassangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassangerServiceImpl implements PassangerService {

    private PassangerRepository passangerRepository;

    @Autowired
    public PassangerServiceImpl(PassangerRepository passangerRepository){
        this.passangerRepository = passangerRepository;
    }

    @Override
    public Page<Passanger> getAllPassangerPaged(int pageNum){
        return passangerRepository.findAll(PageRequest.of(pageNum, 5, Sort.by("lastName")));
    }

    @Override
    public List<Passanger> getAllPassangers(){
        return passangerRepository.findAll();
    }

    @Override
    public Optional<Passanger> getPassangerById(Long passangerId){
        return passangerRepository.findById(passangerId);
    }

    @Override
    public Passanger savePassanger(Passanger passanger){
        return passangerRepository.save(passanger);
    }

    @Override
    public void deletePassangerById(Long passangerId){
        passangerRepository.deleteById(passangerId);
    }
}
