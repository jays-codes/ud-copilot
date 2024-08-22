package jayslabs.copilot.flightreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jayslabs.copilot.flightreservation.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}