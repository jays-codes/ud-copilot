package jayslabs.copilot.flightreservation.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private Long id;
    private boolean checkedIn;
    private int numberOfBags;
    private PassengerDTO passenger;
    private FlightDTO flight;
    private Timestamp created;

}