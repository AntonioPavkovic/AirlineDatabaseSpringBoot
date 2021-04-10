package com.airlinedatabase.airlinedatabase.services;

import com.airlinedatabase.airlinedatabase.exceptions.RecordAlreadyPresentException;
import com.airlinedatabase.airlinedatabase.exceptions.RecordNotFoundException;
import com.airlinedatabase.airlinedatabase.model.Booking;
import com.airlinedatabase.airlinedatabase.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    /*
    * kreiranje nove rezervacije
    */
    public ResponseEntity<Booking> createBooking(Booking newBooking){

        Optional<Booking> findBookingById = bookingRepository.findById(newBooking.getBookingId());

        try{

            if(!findBookingById.isPresent()){
                bookingRepository.save(newBooking);
                return new ResponseEntity<>(newBooking, HttpStatus.OK);
            }
            else
                throw new RecordAlreadyPresentException("Booking with Id: " + newBooking.getBookingId() + " already exists!!");

        }catch (RecordAlreadyPresentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /*
    * update booking
    */
    public Booking updateBooking(Booking changedBooking){

        Optional<Booking> findBookingById = bookingRepository.findById(changedBooking.getBookingId());

        if(findBookingById.isPresent())
            bookingRepository.save(changedBooking);
        else
            throw new RecordNotFoundException(
                    "Booking with Booking Id: " + changedBooking.getBookingId() + " does not exists!!");
        return changedBooking;

    }

    /*
    delete booking
    */
    public String deleteBooking(BigInteger bookingId){

        Optional<Booking> findBookingById = bookingRepository.findById(bookingId);

        if(findBookingById.isPresent()){
            bookingRepository.deleteById(bookingId);
            return "Booking deleted!";
        }
        else
            throw new RecordNotFoundException("Booking not found for the entered BookingID");

    }

    public Iterable<Booking> displayAllBookings() {
        return bookingRepository.findAll();
    }

    public ResponseEntity<?> findBookingById(BigInteger bookingId){

        Optional<Booking> findById = bookingRepository.findById(bookingId);

        try{

            if(findById.isPresent()){
                Booking findBooking = findById.get();
                return new ResponseEntity<Booking>(findBooking, HttpStatus.OK);
            }
            else
                throw new RecordNotFoundException("No record found with ID " + bookingId);

        }catch (RecordNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

}
