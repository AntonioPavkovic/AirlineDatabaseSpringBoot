package com.airlinedatabase.airlinedatabase.services;

import com.airlinedatabase.airlinedatabase.exceptions.RecordAlreadyPresentException;
import com.airlinedatabase.airlinedatabase.exceptions.RecordNotFoundException;
import com.airlinedatabase.airlinedatabase.model.Users;
import com.airlinedatabase.airlinedatabase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> createUser(Users newUser){
        Optional<Users> findUserById = userRepository.findById(newUser.getUserId());

        try{

            if(!findUserById.isPresent()){
                userRepository.save(newUser);
                return new ResponseEntity<Users>(newUser, HttpStatus.OK);
            }
            else
                throw new RecordAlreadyPresentException("User with Id: " + newUser.getUserId() + " already exists!!");

        }catch (RecordAlreadyPresentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public Users updateUser(Users updateUser){
        Optional<Users> findUserById = userRepository.findById(updateUser.getUserId());

        if(findUserById.isPresent())
            userRepository.save(updateUser);
        throw new RecordNotFoundException(
                "User with Id: " + updateUser.getUserId() + " does not exists!!");
    }

    public String deleteUser(BigInteger userId) {
        Optional<Users> findBookingById = userRepository.findById(userId);

        if (findBookingById.isPresent()){
            userRepository.deleteById(userId);
            return "UserDeleted";
        }
        else
            throw new RecordNotFoundException("User not found!");
    }

    public Iterable<Users> displayAllUsers(){
        return userRepository.findAll();
    }

    public ResponseEntity<?> findUserById(BigInteger userId){
        Optional<Users> findById = userRepository.findById(userId);

        try{

            if (findById.isPresent()){
                Users findUser = findById.get();
                return new ResponseEntity<>(findUser, HttpStatus.OK);
            }
            else
                throw new RecordNotFoundException("No record found with ID " + userId);

        }catch (RecordNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

}
