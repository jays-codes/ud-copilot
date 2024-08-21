package jayslabs.copilot.flightreservation.entity;

import java.sql.Timestamp;
import java.util.Comparator;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean checkedIn;

    private int numberOfBags;

    @OneToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    @OneToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @CreationTimestamp
    private Timestamp created;

    public static Comparator<Reservation> getComparatorByPassenger() {
        return Comparator.comparing(reservation -> reservation.getPassenger().getLastName());
    }

    public static Comparator<Reservation> getComparatorByFlight() {
        return Comparator.comparing(reservation -> reservation.getFlight().getDepartureCity());
    }


}

