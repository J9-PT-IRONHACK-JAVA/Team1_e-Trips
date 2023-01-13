const axios = require("axios");

export async function getHotels(
  destination,
  checkInDate,
  checkOutDate,
  guests
) {
  const response = await axios.get(
    `/hotel-search/inspire?cityCode=${destination}&checkInDate=${checkInDate}&checkOutDate=${checkOutDate}&guests=${guests}`
  );
  return response.data;
}
