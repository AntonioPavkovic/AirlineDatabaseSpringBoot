package com.airlinedatabase.airlinedatabase.controller;

import com.airlinedatabase.airlinedatabase.exceptions.RecordAlreadyPresentException;
import com.airlinedatabase.airlinedatabase.model.Booking;
import com.airlinedatabase.airlinedatabase.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@CrossOrigin("http://127.0.0.1:8999/")
@ComponentScan(basePackages = "com")
@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired(required = true)
    BookingService bookingService;

    @PostMapping("/createBookings")
    @ExceptionHandler(RecordAlreadyPresentException.class)
    public void addBookings(@RequestBody Booking newBooking){
        bookingService.createBooking(newBooking);
    }

    @GetMapping("/readAllBookings")
    public Iterable<Booking> readAllBookings() {
        return bookingService.displayAllBookings();
    }

    @PutMapping("/updateBooking")
    @ExceptionHandler(RecordAlreadyPresentException.class)
    public void updateBooking(@RequestBody Booking updateBooking){
        bookingService.updateBooking(updateBooking);
    }

    @GetMapping("/searchBooking/{id}")
    @ExceptionHandler(RecordAlreadyPresentException.class)
    public ResponseEntity<?> searchBookingById(@PathVariable("id")BigInteger bookingId) {
        return bookingService.findBookingById(bookingId);
    }

    @DeleteMapping("/deleteBooking/{id}")
    @ExceptionHandler(RecordAlreadyPresentException.class)
    public void deleteBookingById(@PathVariable("id") BigInteger bookingId){
        bookingService.deleteBooking(bookingId);
    }

}
