package com.airlinedatabase.airlinedatabase.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Schedule")
public class Schedule {

    @Id
    @Column(name = "scheduleId")
    private BigInteger scheduleId;

    @OneToOne(fetch = FetchType.EAGER)
    private Airport departAirport;

    @OneToOne(fetch = FetchType.EAGER)
    private Airport destinationAirport;

    @Column(name = "departureDate")
    @JsonFormat(pattern = "mm-dd-yyyy HH:mm:ss")
    private String departDateTime;

    @Column(name = "arrivalDate")
    @JsonFormat(pattern = "mm-dd-yyyy HH:mm:ss")
    private String arrivalDateTime;

    @Override
    public String toString() {
        return "Schedule {scheduleId=" + scheduleId + ", sourceAirport=" + departAirport + ", destinationAirport="
                + destinationAirport + ", departureDateTime=" + departDateTime + ", arrivalDateTime="
                + arrivalDateTime + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((arrivalDateTime == null) ? 0 : arrivalDateTime.hashCode());
        result = prime * result + ((departDateTime == null) ? 0 : departDateTime.hashCode());
        result = prime * result + ((destinationAirport == null) ? 0 : destinationAirport.hashCode());
        result = prime * result + ((scheduleId == null) ? 0 : scheduleId.hashCode());
        result = prime * result + ((departAirport == null) ? 0 : departAirport.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Schedule other = (Schedule) obj;
        if (arrivalDateTime == null){
            if (other.arrivalDateTime != null)
                return false;
        }
        else if(!arrivalDateTime.equals(other.arrivalDateTime))
            return false;

        if(departDateTime == null) {
            if (other.departDateTime != null)
                return false;
        }
        else if(!destinationAirport.equals(other.destinationAirport))
            return false;

        if(scheduleId == null){
            if(other.scheduleId != null)
                return false;
        }
        else if(!scheduleId.equals(other.scheduleId))
            return false;

        if(departAirport == null){
            if(other.departAirport != null)
                return false;
        }
        else if(!departAirport.equals(other.departAirport))
            return false;

        return true;
    }
}
