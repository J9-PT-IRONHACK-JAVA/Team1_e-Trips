const axios = require("axios");

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
  const response = await axios.post(`/flight-search`, payload);
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
  const response = await axios.post(`/hotel-search`, payload);
  return response.data;
}
