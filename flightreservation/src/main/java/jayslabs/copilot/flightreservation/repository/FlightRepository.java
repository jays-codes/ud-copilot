package jayslabs.copilot.flightreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jayslabs.copilot.flightreservation.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}