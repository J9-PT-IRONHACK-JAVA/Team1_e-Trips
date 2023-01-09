package com.ironhack.backend;


import com.ironhack.backend.service.FlightApiService;
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

        @Override
        public void run(String... args) throws Exception {

            //Only to show that the API proxy is working:
            System.out.println("\nThese are the best flight deals from your selected origin and departure date:\n");
            var printResults = flightApiService.getFlightsOriginDate("MAD", "2023-02-11");
            for (int i = 0; i < printResults.size(); i++) {
                System.out.println(i+1 + ". " + printResults.get(i).toString());
            }
            System.out.println("\n");

        }
}
