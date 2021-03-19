package com.airlinedatabase.airlinedatabase.controler;

import com.airlinedatabase.airlinedatabase.model.Aircraft;
import com.airlinedatabase.airlinedatabase.model.Airport;
import com.airlinedatabase.airlinedatabase.model.Flight;
import com.airlinedatabase.airlinedatabase.model.Passanger;
import com.airlinedatabase.airlinedatabase.services.AircraftService;
import com.airlinedatabase.airlinedatabase.services.AirportService;
import com.airlinedatabase.airlinedatabase.services.FlightService;
import com.airlinedatabase.airlinedatabase.services.PassangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    AirportService airportService;
    @Autowired
    AircraftService aircraftService;
    @Autowired
    FlightService flightService;
    @Autowired
    PassangerService passangerService;

    @GetMapping("/")
    public String showHomePage(){
        return "index";
    }

    @GetMapping("/airport/new")
    public String showAddAirportPage(Model model){
        model.addAttribute("airport", new Airport());
        return "newAirport";
    }

    @PostMapping("/airport/new")
    public String saveAirport(@Valid @ModelAttribute("airport") Airport airport, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("airport", new Airport());
            return "newAirport";
        }
        airportService.saveAirport(airport);
        model.addAttribute("airports", airportService.getAllAirportsPaged(0));
        model.addAttribute("currentPage", 0);
        return "airports";
    }

    @GetMapping("/airport/delete")
    public String deleteAirport(@PathParam("airportId") int airportId, Model model){
        airportService.deleteAirport(airportId);
        model.addAttribute("airports", airportService.getAllAirportsPaged(0));
        model.addAttribute("currentPage", 0);
        return "airports";
    }

    @GetMapping("/airports")
    public String showAirportsList(@RequestParam(defaultValue = "0") int pageNo, Model model){
        model.addAttribute("airports", airportService.getAllAirportsPaged(pageNo));
        model.addAttribute("currentPage", pageNo);
        return "airports";
    }

    @GetMapping("/aircraft/new")
    public String showAddAircraftPage(Model model){
        model.addAttribute("aircraft", new Aircraft());
        return "newAircraft";
    }

    @PostMapping("/aircraft/new")
    public String saveAircraft(@Valid @ModelAttribute("aircraft") Aircraft aircraft, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("aircraft", new Aircraft());
            return "newAircraft";
        }
        aircraftService.saveAircraft(aircraft);
        model.addAttribute("aircraft", aircraftService.getAllAircraftsPaged(0));
        model.addAttribute("currentPage", 0);
        return "aircraft";
    }

    @GetMapping("/aircraft/delete")
    public String deleteAircraft(@PathParam("aircraftId") long aircraftId, Model model){
        aircraftService.deleteAircraft(aircraftId);
        model.addAttribute("aircraft", aircraftService.getAllAircraftsPaged(0));
        model.addAttribute("currentPage", 0);
        return "aircraft";
    }

    @GetMapping("/aircrafts")
    public String showAircraftList(@RequestParam(defaultValue = "0") int pageNo, Model model){
        model.addAttribute("aircraft", aircraftService.getAllAircraftsPaged(pageNo));
        model.addAttribute("currentPage", pageNo);
        return "aircraft";
    }

    @GetMapping("/flight/new")
    public String showNewFlightPage(Model model){
        model.addAttribute("flight", new Flight());
        model.addAttribute("aircraft", aircraftService.getAllAirpcrafts());
        model.addAttribute("airports", airportService.getAllAirports());
        return "newFlight";
    }

    @PostMapping("/flight/new")
    public String saveFlight(@Valid @ModelAttribute("flight") Flight flight, BindingResult bindingResult,
                             @RequestParam("departureAirport") int departureAirport,
                             @RequestParam("destinationAirport") int destinationAirport,
                             @RequestParam("aircraft") long aircraftId,
                             @RequestParam("arrivalTime") String arrivalTime,
                             @RequestParam("departureTime") String departureTime,
                             Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("flight", new Flight());
            model.addAttribute("aircraft", aircraftService.getAllAirpcrafts());
            model.addAttribute("airports", airportService.getAllAirports());
            return "newFlight";
        }

        if(departureAirport == destinationAirport){
            model.addAttribute("sameAirportError", "Departure and destination error can't be the same");
            model.addAttribute("flight", new Flight());
            model.addAttribute("aircraft", aircraftService.getAllAirpcrafts());
            model.addAttribute("airports", airportService.getAllAirports());
            return "newFlight";
        }

        flight.setAircraft(aircraftService.getAircraftById(aircraftId));
        flight.setDepartureAirport(airportService.getAirportById(departureAirport));
        flight.setDestinationAirport(airportService.getAirportById(destinationAirport));
        flight.setDepartureTime(departureTime);
        flight.setArrivalTime(arrivalTime);
        flightService.saveFlight(flight);

        model.addAttribute("flights", flightService.getAllFlightsPaged(0));
        model.addAttribute("currentPage", 0);
        return "flights";
    }

    @GetMapping("/flight/delete")
    private String deleteFlight(@PathParam("flightId") long flightId, Model model){
        flightService.deleteFlightById(flightId);
        model.addAttribute("flights", flightService.getAllFlightsPaged(0));
        model.addAttribute("currentPage", 0);
        return "flights";
    }

    @GetMapping("/flight/search")
    public String showFlightsList(@RequestParam(defaultValue = "0") int pageNo, Model model){
        model.addAttribute("flights", flightService.getAllFlightsPaged(pageNo));
        model.addAttribute("currentPage", pageNo);
        return "flights";
    }

    @GetMapping("/flight/search")
    public String showSearchFlightPage(Model model){
        model.addAttribute("airports", airportService.getAllAirports());
        model.addAttribute("flights", null);
        return "searchFlight";
    }

    @PostMapping("/flight/search")
    public String searchFlight(@RequestParam("departureAirport") int departureAirport,
                               @RequestParam("destinationAirport") int destinationAirport,
                               @RequestParam("departureTime") String departureTime,
                               Model model){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate departTime = LocalDate.parse(departureTime, dateTimeFormatter);
        Airport departAirport = airportService.getAirportById(departureAirport);
        Airport destAirport = airportService.getAirportById(destinationAirport);

        if(departureAirport == destinationAirport){
            model.addAttribute("AirportError", "Departure and destination airport can't be the same");
            model. addAttribute("airports", airportService.getAllAirports());
            return "searchFlight";
        }

        List<Flight> flights = flightService.getAllFlightsByAirportAndDepartureTime(departAirport, destAirport, departTime);
        if(flights.isEmpty()){
            model.addAttribute("notFound", "No record found!");
        }
        else{
            model.addAttribute("flights", flights);
        }

        model.addAttribute("airports", airportService.getAllAirports());
        return "searchFlight";
    }

    @GetMapping("/flight/book")
    public String showBookFlightPage(Model model){
        model.addAttribute("airports", airportService.getAllAirports());
        return "bookFlights";
    }

    @PostMapping("/flight/book")
    public String searchFlightToBook(@RequestParam("departureAirport") int departureAirport,
                                     @RequestParam("destinationAirport") int destinationAirport,
                                     @RequestParam("departureTime") String departureTime,
                                     Model model){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate departTime = LocalDate.parse(departureTime ,dateTimeFormatter);
        Airport departAirport = airportService.getAirportById(destinationAirport);
        Airport destAirport = airportService.getAirportById(destinationAirport);

        if(departureAirport == destinationAirport){
            model.addAttribute("AirportError", "Departure and destination airport can't be the same");
            model.addAttribute("airports", airportService.getAllAirports());
            return "bookFlights";
        }

        List<Flight> flights = flightService.getAllFlightsByAirportAndDepartureTime(departAirport, destAirport, departTime);
        if(flights.isEmpty()){
            model.addAttribute("notFound", "No record found!");
        }
        else{
            model.addAttribute("flights", flights);
        }
        model.addAttribute("airports", airportService.getAllAirports());
        return "bookFlight";
    }

    @GetMapping("/flight/book/new")
    public String showCustomerInfoPage(@RequestParam long flightId, Model model){
        model.addAttribute("flightId", flightId);
        model.addAttribute("passenger", new Passanger());
        return "newPassenger";
    }

    @PostMapping("/flight/book/new")
    public String bookFlight(@Valid @ModelAttribute("passenger")Passanger passanger, BindingResult bindingResult,
                              @RequestParam("flightId") long flightId, Model model){
        Flight flight = flightService.getFlightById(flightId);
        passanger.setFlight(flight);
        passangerService.savePassanger(passanger);
        model.addAttribute("passenger", passanger);
        return "confirmationPage";
    }

    @GetMapping("/flight/book/verify")
    public String showVerifyBookingPage(){
        return "verifyBookingPage";
    }

    public String showVerifyBookingPageResult(@RequestParam("flightId") long flightId,
                                              @RequestParam("passengerId") long passengerId,
                                              Model model){
        Flight flight = flightService.getFlightById(flightId);
        if(flight != null){
            model.addAttribute("flight", flight);
            List<Passanger> passengers = flight.getPassengers();
            Optional<Passanger> passanger = null;

            for(Passanger p : passengers){
                if (p.getPassangerId() == passengerId) {
                    passanger = passangerService.getPassangerById(passengerId);
                    model.addAttribute("passenger", passanger);
                }
            }

            if(passanger != null){
                return "verifyBooking";
            }
            else{
                model.addAttribute("notFound", "Not Found");
                return "verifyBooking";
            }
        }else{
            model.addAttribute("notFound", "Not Found");
            return "verifyBooking";
        }
    }

    @PostMapping("/flight/book/cancel")
    public String cancelTicket(@RequestParam("passengerId") long passengerId, Model model){
        passangerService.deletePassangerById(passengerId);
        model.addAttribute("flights", flightService.getAllFlightsPaged(0));
        model.addAttribute("currentPage", 0);
        return "flights";
    }

    @GetMapping("passengers")
    public String showPassengersList(@RequestParam long flightId, Model model){
        List<Passanger> passengers = flightService.getFlightById(flightId).getPassengers();
        model.addAttribute("passengers", passengers);
        model.addAttribute("flight", flightService.getFlightById(flightId));
        return "passengers";
    }

    @GetMapping("/login")
    public String showLoginPage(){
        return "login";
    }

    @GetMapping("fancy")
    public String showLoginPage1(){
        return "index";
    }
}