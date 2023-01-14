package com.ironhack.backend.service;

import com.ironhack.backend.dto.*;
import com.ironhack.backend.exceptions.BookingNotFoundException;
import com.ironhack.backend.exceptions.UserNotFoundException;
import com.ironhack.backend.model.Booking;
import com.ironhack.backend.model.FlightBooking;
import com.ironhack.backend.model.HotelBooking;
import com.ironhack.backend.repository.BookingRepository;
import com.ironhack.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserService userService;

    public BookingService(BookingRepository bookingRepository, UserService userService) {
        this.bookingRepository = bookingRepository;
        this.userService = userService;
    }

    public Booking findById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));
    }

    public MyBookingsDTO findAllByUser(Long id) {

        var allBookings = bookingRepository.findAllByUserUserId(id);
        List<FlightBookingDTO> listOfFlightBookingDTO = new ArrayList<>();
        List<HotelBookingDTO> listOfHotelBookingDTO = new ArrayList<>();
        for(Booking booking : allBookings){
            if (booking instanceof FlightBooking) listOfFlightBookingDTO.add(FlightBookingDTO.fromFlightBooking((FlightBooking) booking));
            else listOfHotelBookingDTO.add(HotelBookingDTO.fromHotelBooking((HotelBooking) booking));
        }
        return new MyBookingsDTO(listOfFlightBookingDTO, listOfHotelBookingDTO);
    }

    public HotelBookingDTO saveHotel(Long userId, HotelDTO hotelDTO) {
        var user = userService.findUserById(userId);
        return HotelBookingDTO.fromHotelBooking(bookingRepository
                .save(HotelBooking.fromHotelBookingDTO(user, HotelBookingDTO.fromHotelDTO(hotelDTO))));
    }

    public FlightBookingDTO saveFlight(Long userId, FlightDTO flightDTO) {
        var user = userService.findUserById(userId);
        return FlightBookingDTO.fromFlightBooking(bookingRepository
                .save(FlightBooking.fromFlightBookingDTO(user, FlightBookingDTO.fromFlightDTO(flightDTO))));
    }

    public List<Booking> saveAll(List<Booking> listOfBookings) {
        return bookingRepository.saveAll(listOfBookings);
    }

    public void delete(Long id){  bookingRepository.deleteById(id);}


    public BookingDTO updateBooking(Long bookingId, Optional<String> name, Optional<Integer> travelers, Optional<String> purpose) {
        var bookingToUpdate = findById(bookingId);
        var bookingType = bookingToUpdate.getBookingType();

        name.ifPresent(bookingToUpdate::setName);
        travelers.ifPresent(bookingToUpdate::setTravelers);
        purpose.ifPresent(bookingToUpdate::setPurpose);

        bookingRepository.save(bookingToUpdate);

        return BookingDTO.fromBooking(bookingToUpdate);
    }


}
