package jayslabs.copilot.flightreservation.mapper;

import jayslabs.copilot.flightreservation.dto.PassengerDTO;
import jayslabs.copilot.flightreservation.entity.Passenger;

public interface PassengerMapper {
    Passenger toEntity(PassengerDTO dto);
    PassengerDTO toDto(Passenger entity);
}