package jayslabs.copilot.flightreservation.entity;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;

    private String operatingAirlines;

    private String departureCity;

    private String arrivalCity;

    private Date dateOfDeparture;

    private Timestamp estimatedDepartureTime;

}