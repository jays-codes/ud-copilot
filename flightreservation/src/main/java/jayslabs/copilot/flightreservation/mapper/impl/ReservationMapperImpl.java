package jayslabs.copilot.flightreservation.mapper.impl;

import org.springframework.stereotype.Component;

import jayslabs.copilot.flightreservation.dto.ReservationDTO;
import jayslabs.copilot.flightreservation.entity.Reservation;
import jayslabs.copilot.flightreservation.mapper.FlightMapper;
import jayslabs.copilot.flightreservation.mapper.PassengerMapper;
import jayslabs.copilot.flightreservation.mapper.ReservationMapper;

@Component
public class ReservationMapperImpl implements ReservationMapper {

    private final PassengerMapper passengerMapper;
    private final FlightMapper flightMapper;

    public ReservationMapperImpl(PassengerMapper passengerMapper, FlightMapper flightMapper) {
        this.passengerMapper = passengerMapper;
        this.flightMapper = flightMapper;
    }

    @Override
    public Reservation toEntity(ReservationDTO dto) {
        if (dto == null) {
            return null;
        }

        Reservation entity = new Reservation();
        entity.setId(dto.getId());
        entity.setCheckedIn(dto.isCheckedIn());
        entity.setNumberOfBags(dto.getNumberOfBags());
        entity.setPassenger(passengerMapper.toEntity(dto.getPassenger()));
        entity.setFlight(flightMapper.toEntity(dto.getFlight()));
        entity.setCreated(dto.getCreated());

        return entity;
    }

    @Override
    public ReservationDTO toDto(Reservation entity) {
        if (entity == null) {
            return null;
        }

        return new ReservationDTO(
            entity.getId(),
            entity.isCheckedIn(),
            entity.getNumberOfBags(),
            passengerMapper.toDto(entity.getPassenger()),
            flightMapper.toDto(entity.getFlight()),
            entity.getCreated()
        );
    }
}