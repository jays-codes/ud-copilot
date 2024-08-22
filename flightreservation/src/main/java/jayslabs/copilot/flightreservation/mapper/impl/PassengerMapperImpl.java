package jayslabs.copilot.flightreservation.mapper.impl;

import org.springframework.stereotype.Component;

import jayslabs.copilot.flightreservation.dto.PassengerDTO;
import jayslabs.copilot.flightreservation.entity.Passenger;
import jayslabs.copilot.flightreservation.mapper.PassengerMapper;

@Component
public class PassengerMapperImpl implements PassengerMapper {

    @Override
    public Passenger toEntity(PassengerDTO dto) {
        if (dto == null) {
            return null;
        }

        Passenger entity = new Passenger();
        entity.setId(dto.getId());
        entity.setPassportNumber(dto.getPassportNumber());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());

        return entity;
    }

    @Override
    public PassengerDTO toDto(Passenger entity) {
        if (entity == null) {
            return null;
        }

        return new PassengerDTO(
            entity.getId(),
            entity.getPassportNumber(),
            entity.getFirstName(),
            entity.getLastName(),
            entity.getMiddleName(),
            entity.getEmail(),
            entity.getPhone()
        );
    }
}