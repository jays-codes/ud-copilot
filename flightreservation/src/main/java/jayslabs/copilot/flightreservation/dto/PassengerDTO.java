package jayslabs.copilot.flightreservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDTO {

    private Long id;
    private String passportNumber;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phone;

}