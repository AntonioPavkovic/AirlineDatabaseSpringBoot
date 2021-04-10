package com.airlinedatabase.airlinedatabase.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduledFlight {

    @Id
    @Column(name = "scheduleFlightId")
    private BigInteger scheduleFlightId;

    @OneToOne(fetch = FetchType.EAGER)
    @NotNull
    private Flight flight;

    @Column(name = "availableSeats")
    @NotNull
    private Integer availableSeats;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Schedule schedule;

    @Override
    public String toString() {
        return "ScheduledFlight {scheduleFlightId=" + scheduleFlightId + ", flight=" + flight + ", availableSeats="
                + availableSeats + ", schedule=" + schedule + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((availableSeats == null) ? 0 : availableSeats.hashCode());
        result = prime * result + ((flight == null) ? 0 : flight.hashCode());
        result = prime * result + ((schedule == null) ? 0 : schedule.hashCode());
        result = prime * result + ((scheduleFlightId == null) ? 0 : scheduleFlightId.hashCode());
        return result;
    }


    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ScheduledFlight other = (ScheduledFlight) obj;
        if (availableSeats == null) {
            if (other.availableSeats != null)
                return false;
        } else if (!availableSeats.equals(other.availableSeats))
            return false;
        if (flight == null) {
            if (other.flight != null)
                return false;
        } else if (!flight.equals(other.flight))
            return false;
        if (schedule == null) {
            if (other.schedule != null)
                return false;
        } else if (!schedule.equals(other.schedule))
            return false;
        if (scheduleFlightId == null) {
            if (other.scheduleFlightId != null)
                return false;
        } else if (!scheduleFlightId.equals(other.scheduleFlightId))
            return false;
        return true;
    }
}
