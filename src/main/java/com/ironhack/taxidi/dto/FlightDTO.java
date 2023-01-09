package com.ironhack.taxidi.dto;

import com.ironhack.taxidi.dto.amadeusFlightsDTOs.Price;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
public class FlightDTO {
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private BigDecimal price;
}
