package com.ironhack.taxidi;

import com.ironhack.taxidi.proxy.FlightsProxy;
import com.ironhack.taxidi.service.FlightApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Optional;

@SpringBootApplication
@EnableFeignClients
@RequiredArgsConstructor
public class TaxidiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TaxidiApplication.class, args);
    }

    private final FlightApiService flightApiService;

    @Override
    public void run(String... args) throws Exception {

        //Only to show that the API proxy is working:
        System.out.println("\nThese are the best flight deals from your selected origin and departure date:\n");
        var printResults = flightApiService.getFlightsOriginDate(Optional.of("MAD"), Optional.of("2023-02-11"));
        for (int i = 0; i < printResults.size(); i++) {
            System.out.println(i+1 + ". " + printResults.get(i).toString());
        }
        System.out.println("\n");

    }
}
