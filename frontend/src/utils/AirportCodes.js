var airports = require("airport-codes");

export function getAirportName(iataCode) {
  return airports.findWhere({ iata: iataCode }).get("name");
}
