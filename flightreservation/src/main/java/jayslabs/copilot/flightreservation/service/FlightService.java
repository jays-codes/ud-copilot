package jayslabs.copilot.flightreservation.service;

import java.util.List;

import jayslabs.copilot.flightreservation.dto.FlightDTO;

public interface FlightService {
    FlightDTO createFlight(FlightDTO flightDTO);
    FlightDTO getFlightById(Long id);
    List<FlightDTO> getAllFlights();
    FlightDTO updateFlight(Long id, FlightDTO flightDTO);
    void deleteFlight(Long id);
}