package jayslabs.copilot.flightreservation.mapper;

import jayslabs.copilot.flightreservation.dto.ReservationDTO;
import jayslabs.copilot.flightreservation.entity.Reservation;

public interface ReservationMapper {
    Reservation toEntity(ReservationDTO dto);
    ReservationDTO toDto(Reservation entity);
}