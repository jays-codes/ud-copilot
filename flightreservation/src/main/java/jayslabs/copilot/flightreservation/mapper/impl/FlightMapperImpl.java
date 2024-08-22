package jayslabs.copilot.flightreservation.mapper.impl;

import org.springframework.stereotype.Component;

import jayslabs.copilot.flightreservation.dto.FlightDTO;
import jayslabs.copilot.flightreservation.entity.Flight;
import jayslabs.copilot.flightreservation.mapper.FlightMapper;

@Component
public class FlightMapperImpl implements FlightMapper {

    @Override
    public Flight toEntity(FlightDTO dto) {
        if (dto == null) {
            return null;
        }

        Flight entity = new Flight();
        entity.setFlightNumber(dto.getFlightNumber());
        entity.setOperatingAirlines(dto.getOperatingAirlines());
        entity.setDepartureCity(dto.getDepartureCity());
        entity.setArrivalCity(dto.getArrivalCity());
        entity.setDateOfDeparture(dto.getDateOfDeparture());
        entity.setEstimatedDepartureTime(dto.getEstimatedDepartureTime());

        return entity;
    }

    @Override
    public FlightDTO toDto(Flight entity) {
        if (entity == null) {
            return null;
        }

        return new FlightDTO(
            entity.getId(),
            entity.getFlightNumber(),
            entity.getOperatingAirlines(),
            entity.getDepartureCity(),
            entity.getArrivalCity(),
            entity.getDateOfDeparture(),
            entity.getEstimatedDepartureTime()
        );
    }
}