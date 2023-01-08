const axios = require('axios');

//GET token from amadeus and send it below inside header of flights
export async function getFlights(origin) {

    const response = await axios.get('/flights',  { params: { origin: origin } });
    return response.data;
}

//GET flights by passing the origin
export async function getFlights(origin) {

    const response = await axios.get('/flights',  { params: { origin: origin } });
    return response.data;
}
//POST flight to reserve it (add it in the database)
export async function createBooking(data) {
    const response = await axios.post(`/booking`, {booking: data});
    return response.data;
}

//PUT flight to modify its details in the database
export async function editBooking(data) {
    const response = await axios.put(`/booking`, {booking: data});
    return response.data;
}

//DELETE flight to reserve it (add it in the database)
export async function editBooking(IdToDelete) {
    const response = await axios.delete(`/booking/${IdToDelete}`);
    return response.data;
}