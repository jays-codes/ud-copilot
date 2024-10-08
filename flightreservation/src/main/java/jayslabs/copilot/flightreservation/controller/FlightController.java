package jayslabs.copilot.flightreservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FlightController {

    @GetMapping("/findFlights")
    public String displayFindFlights() {
        return "findFlights";
    }
}