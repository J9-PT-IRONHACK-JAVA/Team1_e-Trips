package com.ironhack.taxidi.service;

import com.ironhack.taxidi.dto.FlightDTO;
import com.ironhack.taxidi.dto.amadeusFlightsDTOs.FlightJson;
import com.ironhack.taxidi.dto.amadeusFlightsDTOs.InspirationResponse;
import com.ironhack.taxidi.proxy.FlightsProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightApiService {

    private final FlightsProxy flightsProxy;

    public List<FlightDTO> getFlightsOriginDate(String origin, String departureDate){
        List<FlightDTO> flightResultList = new ArrayList<>();
        var response = flightsProxy.getInspirationFlights(origin, departureDate);

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
