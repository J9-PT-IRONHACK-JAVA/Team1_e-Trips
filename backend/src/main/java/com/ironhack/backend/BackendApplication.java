package com.ironhack.backend;


import com.ironhack.backend.service.FlightApiService;
import com.ironhack.backend.service.HotelsApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@EnableFeignClients
@RequiredArgsConstructor
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class BackendApplication implements CommandLineRunner {

        public static void main(String[] args) {
            SpringApplication.run(BackendApplication.class, args);
        }

        private final FlightApiService flightApiService;
        private final HotelsApiService hotelsApiService;

        @Override
        public void run(String... args) throws Exception {

//            //Only to show that the API proxy is working:
//            System.out.println("\nThese are the best flight deals from your selected origin and departure date:\n");
//            var printFlightResults = flightApiService.getFlightsByOriginAndDate("LON", "2023-02-11");
//            for (int i = 0; i < printFlightResults.size(); i++) {
//                System.out.println(i+1 + ". " + printFlightResults.get(i).toString());
//            }
//            System.out.println("\n");
//
//            System.out.println("\nAnd some hotels for LON:\n");
//            var printHotelResults = hotelsApiService.getHotelOffers("LON", "2023-02-11", "2023-02-15", 2);
//            for (int i = 0; i < printHotelResults.size(); i++) {
//                System.out.println(i+1 + ". " + printHotelResults.get(i).toString());
//            }
//            System.out.println("\n");
//
//            System.out.println("\nAnd some hotels for MAD:\n");
//            var printSimulatedHotelResults = hotelsApiService.getHotelOffers("MAD", "2023-02-11", "2023-02-15", 2);
//            for (int i = 0; i < printSimulatedHotelResults.size(); i++) {
//                System.out.println(i+1 + ". " + printSimulatedHotelResults.get(i).toString());
//            }
//            System.out.println("\n");

                System.out.println(flightApiService.getFlightsByOriginAndDate("MAD", "2023-01-01"));
                System.out.println(hotelsApiService.getHotelOffers("LON", "2023-02-22","2023-03-11",2));

        }
}
