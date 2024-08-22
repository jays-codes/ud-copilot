package jayslabs.copilot.flightreservation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "PASSENGER")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String passportNumber;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phone;
    
    // Add constructors, getters, and setters if needed
}