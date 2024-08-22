package jayslabs.copilot.flightreservation.mapper;

import jayslabs.copilot.flightreservation.dto.FlightDTO;
import jayslabs.copilot.flightreservation.entity.Flight;

public interface FlightMapper {
    Flight toEntity(FlightDTO dto);
    FlightDTO toDto(Flight entity);
}