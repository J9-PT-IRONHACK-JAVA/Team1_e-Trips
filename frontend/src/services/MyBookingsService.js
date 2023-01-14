const axios = require("axios");
import { store } from "@/store";

export async function getBookings(userId) {
  const response = await axios.get(`/my-bookings/${userId}`);
  return response.data;
}

export async function createFlightBooking(
  origin,
  destination,
  departureDate,
  returnDate,
  price
) {
  const payload = {
    origin: origin,
    destination: destination,
    departureDate: departureDate,
    returnDate: returnDate,
    price: price,
  };
  const response = await axios.post(
    `/my-bookings/save-flight?userId=${store.state.user.user_id}`,
    payload
  );
  return response.data;
}

export async function createHotelBooking(
  name,
  checkInDate,
  checkOutDate,
  guests,
  price
) {
  const payload = {
    name: name,
    checkInDate: checkInDate,
    checkOutDate: checkOutDate,
    guests: guests,
    price: price,
  };
  const response = await axios.post(
    `/my-bookings/save-flight?userId=${store.state.user.user_id}`,
    payload
  );
  return response.data;
}

export async function cancelBooking(bookingId) {
  const response = await axios.delete(`my-bookings?booking=${bookingId}`);
  return response.data;
}
