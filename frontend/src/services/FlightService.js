const axios = require("axios");

export async function getFlightsByOrigin(origin) {
  const response = await axios.get(`/flight-search/inspire?origin=${origin}`);
  return response.data;
}
export async function getFlightsByDate(departureDate) {
  const response = await axios.get(
    `/flight-search/inspire?departureDate=${departureDate}`
  );
  return response.data;
}
export async function getFlights(origin, departureDate) {
  const response = await axios.get(
    `/flight-search/inspire?origin=${origin}&departureDate=${departureDate}`
  );
  return response.data;
}
