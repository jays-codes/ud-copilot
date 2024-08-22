package jayslabs.copilot.flightreservation.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jayslabs.copilot.flightreservation.dto.FlightDTO;
import jayslabs.copilot.flightreservation.entity.Flight;
import jayslabs.copilot.flightreservation.mapper.FlightMapper;
import jayslabs.copilot.flightreservation.repository.FlightRepository;
import jayslabs.copilot.flightreservation.service.FlightService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    @Override
    @Transactional
    public FlightDTO createFlight(FlightDTO flightDTO) {
        Flight flight = flightMapper.toEntity(flightDTO);
        flight = flightRepository.save(flight);
        return flightMapper.toDto(flight);
    }

    @Override
    @Transactional(readOnly = true)
    public FlightDTO getFlightById(Long id) {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new RuntimeException("Flight not found"));
        return flightMapper.toDto(flight);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FlightDTO updateFlight(Long id, FlightDTO flightDTO) {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new RuntimeException("Flight not found"));
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setOperatingAirlines(flightDTO.getOperatingAirlines());
        flight.setDepartureCity(flightDTO.getDepartureCity());
        flight.setArrivalCity(flightDTO.getArrivalCity());
        flight.setDateOfDeparture(flightDTO.getDateOfDeparture());
        flight.setEstimatedDepartureTime(flightDTO.getEstimatedDepartureTime());
        flight = flightRepository.save(flight);
        return flightMapper.toDto(flight);
    }

    @Override
    @Transactional
    public void deleteFlight(Long id) {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new RuntimeException("Flight not found"));
        flightRepository.delete(flight);
    }
}