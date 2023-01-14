package com.ironhack.backend.service;

import com.ironhack.backend.dto.HotelDTO;
import com.ironhack.backend.dto.amadeusFlightsDTOs.*;
import com.ironhack.backend.proxy.AmadeusProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelsApiService {

    private final AmadeusProxy amadeusProxy;

    public List<HotelDTO> getHotelOffers(String cityCode, String checkInDate, String checkOutDate, Integer guests){

        try {
            if (cityCode.equals("LON")) {
                var availabilityResponse =
                        amadeusProxy.getAvailability(getArrayOfHotelsIdInCity(cityCode), guests, checkInDate, checkOutDate);

                List<HotelDTO> listOfHotels = new ArrayList<>();
                for (HotelAvailableOffersJson hotelOffer : availabilityResponse.getData()) {
                    var hotelResult = new HotelDTO();
                    hotelResult.setName(hotelOffer.getHotel().getName());
                    hotelResult.setPrice(BigDecimal.valueOf(Double.parseDouble(hotelOffer.getOffers().get(0).getPrice().getTotal())));
                    hotelResult.setCheckInDate(LocalDate.parse(checkInDate));
                    hotelResult.setCheckOutDate(LocalDate.parse(checkOutDate));
                    hotelResult.setGuests(guests);

                    listOfHotels.add(hotelResult);
                }
                return listOfHotels;
            } else return getSimulatedHotelOffers(cityCode, checkInDate, checkOutDate, guests);

        } catch (Exception e) {
            List<HotelDTO> listOfHotels = new ArrayList<>(List.of(
                    new HotelDTO("Oxford Class Hotel", LocalDate.parse(checkInDate), LocalDate.parse(checkOutDate), guests, generateRandomPrice()),
                    new HotelDTO("DoubleTree Camden", LocalDate.parse(checkInDate), LocalDate.parse(checkOutDate), guests, generateRandomPrice()),
                    new HotelDTO("Apex City of London", LocalDate.parse(checkInDate), LocalDate.parse(checkOutDate), guests, generateRandomPrice()),
                    new HotelDTO("Walkie-Talkie Ritz", LocalDate.parse(checkInDate), LocalDate.parse(checkOutDate), guests, generateRandomPrice())
            ));
            return listOfHotels;
        }
    }

    private String[] getArrayOfHotelsIdInCity(String cityCode) {
        Integer[] stars = {4};
        var response = amadeusProxy.getHotelsByCityAndRating(cityCode, stars);
        List<String> listOfHotelsIdInCity = new ArrayList<>();
        for (HotelJson hotel : response.getData()) {
            listOfHotelsIdInCity.add(hotel.getHotelId());
        }
        return listOfHotelsIdInCity.toArray(new String[0]);
    }

    private List<HotelDTO> getSimulatedHotelOffers(String cityCode, String checkInDate, String checkOutDate, Integer guests) {
        Integer[] stars = {5};
        var response = amadeusProxy.getHotelsByCityAndRating(cityCode, stars);
        List<HotelDTO> listOfHotelsInCity = new ArrayList<>();
        for (HotelJson hotel : response.getData()) {
            var hotelResult = new HotelDTO();
            hotelResult.setName(hotel.getName());
            hotelResult.setCheckInDate(LocalDate.parse(checkInDate));
            hotelResult.setCheckOutDate(LocalDate.parse(checkOutDate));
            hotelResult.setPrice(generateRandomPrice());
            hotelResult.setGuests(guests);

            listOfHotelsInCity.add(hotelResult);
        }
        return listOfHotelsInCity;
    }

    private static BigDecimal generateRandomPrice() {
        return BigDecimal.valueOf(300 + 600 * Math.random()).setScale(2, RoundingMode.HALF_UP);
    }

}
