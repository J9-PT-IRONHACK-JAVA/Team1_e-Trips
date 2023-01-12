package com.ironhack.backend.proxy;

import com.ironhack.backend.config.HotelBedsConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "hotels-api", url = "https://api.test.hotelbeds.com",configuration = HotelBedsConfiguration.class)
public interface HotelsProxy {

//    @GetMapping("/hotel-api/1.0/hotels")
//    HotelResponse checkAvailabilityHotels(@RequestBody HotelSearchDTO hotelSearchDTO);

}

/*
{
        "stay": {
        "checkIn": "2020-06-15",
        "checkOut": "2020-06-16"
        },
        "occupancies": [
        {
        "rooms": 1,
        "adults": 2,
        "children": 0
        }
        ],
        "hotels": {
        "hotel": [
        77,
        168,
        264,
        265,
        297,
        311
        ]
        }
        }
*/
