package com.airlinedatabase.airlinedatabase.services;

import com.airlinedatabase.airlinedatabase.exceptions.RecordNotFoundException;
import com.airlinedatabase.airlinedatabase.exceptions.ScheduledFlightNotFoundException;
import com.airlinedatabase.airlinedatabase.model.Booking;
import com.airlinedatabase.airlinedatabase.model.Schedule;
import com.airlinedatabase.airlinedatabase.model.ScheduledFlight;
import com.airlinedatabase.airlinedatabase.repository.ScheduleRepository;
import com.airlinedatabase.airlinedatabase.repository.ScheduledFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
public class ScheduledFlightService {

    @Autowired
    ScheduledFlightRepository scheduledFlightRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    BookingService bookingService;

    /*
    * Service metoda koja dodaje novi "Scheduled" let u bazu
    */
    public ScheduledFlight addScheduledFlight(ScheduledFlight scheduledFlight){
        return scheduledFlightRepository.save(scheduledFlight);
    }

    public ScheduledFlight modifyScheduledFlight(ScheduledFlight scheduledFlight){

        ScheduledFlight updateScheduledFlight = scheduledFlightRepository.findById(scheduledFlight.getScheduleFlightId()).get();
        Schedule updateSchedule  = scheduleRepository.findById(scheduledFlight.getSchedule().getScheduleId()).get();
        updateScheduledFlight.setAvailableSeats(scheduledFlight.getAvailableSeats());
        updateSchedule.setDepartAirport(scheduledFlight.getSchedule().getDepartAirport());
        updateSchedule.setDestinationAirport(scheduledFlight.getSchedule().getDestinationAirport());
        updateSchedule.setArrivalDateTime(scheduledFlight.getSchedule().getArrivalDateTime());
        updateSchedule.setDepartDateTime(scheduledFlight.getSchedule().getDepartDateTime());
        scheduledFlightRepository.save(updateScheduledFlight);

        return scheduledFlight;
    }

    public String removeScheduledFlight(BigInteger flightId) throws RecordNotFoundException {
        if (flightId == null)
            throw new RecordNotFoundException("Enter flight Id");
        Optional<ScheduledFlight> scheduleFlight = scheduledFlightRepository.findById(flightId);
        if (!scheduleFlight.isPresent())
            throw new RecordNotFoundException("Enter a valid Flight Id");
        else {
            // try {
            // cancelBookings(flightId);
            // } catch (RecordNotFoundException e) {
            // System.out.println("No Bookings Found");
            // }
            scheduledFlightRepository.deleteById(flightId);
        }
        return "Scheduled flight with ID " + flightId + " is not found";
    }


    public boolean cancelBookings(BigInteger flightId) throws RecordNotFoundException{
        Iterable<Booking> bookingList = bookingService.displayAllBookings();
        for (Booking booking : bookingList) {
            /*if (booking.getScheduleFlight().getScheduleFlightId().equals(flightId)) {
                bookingService.deleteBooking(booking.getBookingId());
            }*/
        }
        return true;
    }

    public Iterable<ScheduledFlight> viewAllScheduledFlights() {
        return scheduledFlightRepository.findAll();
    }

    /*
    pregled letova po ID-u
    */
    public ScheduledFlight viewScheduledFlight(BigInteger flightId) throws ScheduledFlightNotFoundException{

        if(flightId == null)
            throw new ScheduledFlightNotFoundException("Enter flight ID: ");

        Optional<ScheduledFlight> scheduledFlight = scheduledFlightRepository.findById(flightId);

        if (!scheduledFlight.isPresent())
            throw new ScheduledFlightNotFoundException("Enter a valid Flight Id");
        else
            return scheduledFlight.get();

    }

}
