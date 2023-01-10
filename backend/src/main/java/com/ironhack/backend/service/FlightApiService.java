package com.ironhack.backend.service;

import com.ironhack.backend.dto.FlightDTO;
import com.ironhack.backend.dto.amadeusFlightsDTOs.FlightJson;
import com.ironhack.backend.dto.amadeusFlightsDTOs.InspirationResponse;
import com.ironhack.backend.proxy.FlightsProxy;
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

    public List<FlightDTO> getFlightsByOriginAndDate(String origin, String departureDate){
        var response = flightsProxy.getInspirationFlights(origin, departureDate);

        return convertFlightDTOListFromProxyResponse(response);
    }

    public List<FlightDTO> getFlightsByOrigin(String origin){
        var response = flightsProxy.getInspirationFlightsOnlyOrigin(origin);

        return convertFlightDTOListFromProxyResponse(response);
    }

    private List<FlightDTO> convertFlightDTOListFromProxyResponse(InspirationResponse response) {
        List<FlightDTO> flightResultList = new ArrayList<>();
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
