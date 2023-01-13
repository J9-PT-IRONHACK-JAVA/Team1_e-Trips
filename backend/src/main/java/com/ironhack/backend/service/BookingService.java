package com.ironhack.backend.service;

import com.ironhack.backend.dto.BookingDTO;
import com.ironhack.backend.dto.FlightBookingDTO;
import com.ironhack.backend.dto.FlightDTO;
import com.ironhack.backend.model.Booking;
import com.ironhack.backend.model.FlightBooking;
import com.ironhack.backend.model.HotelBooking;
import com.ironhack.backend.repository.BookingRepository;
import com.ironhack.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public List<Booking> findAllByUser(Long id) {
        return bookingRepository.findAllByUserUserId(id);
    }

    public HotelBooking saveHotel(HotelBooking hotelbooking) {
        return bookingRepository.save(hotelbooking);
    }

    public FlightBookingDTO saveFlight(Long userId, FlightDTO flightDTO) {
// flightDto -> flightBookingDto -> BookingDto/Booking
// save booking -> flightBookingDto ->
        var user = userRepository.findById(userId);
        var returnDto = FlightBookingDTO.fromFlightBooking(bookingRepository
                .save(FlightBooking.fromDTO(user, FlightBookingDTO.fromFlightDTO(flightDTO))));

//        FlightBookingDTO.fromFlightBooking(bookingService.saveFlight(FlightBooking.fromDTO(FlightBookingDTO.fromFlightDTO(flightDTO))));
        return returnDto;
    }

    public List<Booking> saveAll(List<Booking> listOfBookings) {
        return bookingRepository.saveAll(listOfBookings);
    }

    public void delete(Long id){  bookingRepository.deleteById(id);}


    public BookingDTO updateBooking(Long bookingId, Optional<String> name, Optional<Integer> travelers, Optional<String> purpose) {
        var bookingToUpdate = bookingRepository.findById(bookingId);

        name.ifPresent(bookingToUpdate.get()::setName);
        travelers.ifPresent(bookingToUpdate.get()::setTravelers);
        purpose.ifPresent(bookingToUpdate.get()::setPurpose);

        bookingRepository.save(bookingToUpdate.get());

        return BookingDTO.fromBooking(bookingToUpdate.get());
    }


}
