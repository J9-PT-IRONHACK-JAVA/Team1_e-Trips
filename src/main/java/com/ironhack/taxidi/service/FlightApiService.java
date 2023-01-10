package com.ironhack.taxidi.service;

import com.ironhack.taxidi.dto.FlightDTO;
import com.ironhack.taxidi.dto.amadeusFlightsDTOs.FlightJson;
import com.ironhack.taxidi.proxy.FlightsProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightApiService {

    private final FlightsProxy flightsProxy;

    public List<FlightDTO> getFlightsOrigin(Optional<String> optionalOrigin){
        List<FlightDTO> flightResultList = new ArrayList<>();

        var response = flightsProxy.getInspirationFlights(optionalOrigin.get());

        for(FlightJson flight : response.getData()){
            var flightResult = new FlightDTO();
            flightResult.setOrigin(flight.getOrigin());
            flightResult.setDestination(flight.getDestination());
            flightResult.setDepartureDate(LocalDate.parse(flight.getDepartureDate()));
            flightResult.setReturnDate(LocalDate.parse(flight.getReturnDate()));
            flightResult.setPrice(BigDecimal.valueOf(flight.getPrice().getTotal()));

            flightResultList.add(flightResult);
        }
        return flightResultList;
    }

    public List<FlightDTO> getFlightsOriginDate(Optional<String> optionalOrigin, Optional<String> optionalDepartureDate){
        List<FlightDTO> flightResultList = new ArrayList<>();

        var response = flightsProxy.getInspirationFlights(optionalOrigin.get(), optionalDepartureDate.get());

        for(FlightJson flight : response.getData()){
            var flightResult = new FlightDTO();
            flightResult.setOrigin(flight.getOrigin());
            flightResult.setDestination(flight.getDestination());
            flightResult.setDepartureDate(LocalDate.parse(flight.getDepartureDate()));
            flightResult.setReturnDate(LocalDate.parse(flight.getReturnDate()));
            flightResult.setPrice(BigDecimal.valueOf(flight.getPrice().getTotal()));

            flightResultList.add(flightResult);
        }

        return flightResultList;
    }

}
