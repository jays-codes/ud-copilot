package jayslabs.copilot.flightreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jayslabs.copilot.flightreservation.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}