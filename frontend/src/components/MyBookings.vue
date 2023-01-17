<template>
  <div>
    <div><h1>My flights</h1></div>

    <div class="card-container" :key="componentKey">
      <BookingCard
        v-for="(flight, index) in flights"
        :key="index"
        :id="index"
        :inBookings="true"
        @deleteBooking="deleteBooking(flight)"
      >
        <template #image>
          <img
            :src="`https://picsum.photos/id/${
              index + Math.floor(Math.random() * (180 - 30)) + 30
            }/500/300`"
          />
        </template>
        <template #destination>
          {{ formatAirport(flight.origin) }} -
          {{ formatAirport(flight.destination) }}
        </template>
        <template #departureDate>
          Departure: {{ formatDate(flight.departureDate) }}
        </template>
        <template #returnDate>
          Return: {{ formatDate(flight.arrivalDate) }}</template
        >
        <template #price> Price: {{ flight.price }} EUR</template>
      </BookingCard>
    </div>
    <div><h1>My hotels</h1></div>
    <div class="card-container" :key="componentKey">
      <BookingCard
        v-for="(hotel, index) in hotels"
        :key="index"
        :id="index"
        :inBookings="true"
        @deleteBooking="deleteBooking(hotel)"
      >
        <template #image>
          <img
            :src="`https://picsum.photos/id/${
              index + Math.floor(Math.random() * (180 - 30)) + 30
            }/500/300`"
          />
        </template>
        <template #destination>Hotel name: {{ hotel.hotelName }}</template>
        <template #departureDate> Check in: {{ hotel.arrivalDate }} </template>
        <template #returnDate> Check out:{{ hotel.departureDate }}</template>
        <template #price> {{ hotel.price }}</template>
      </BookingCard>
    </div>
  </div>
</template>

<script>
import BookingCard from "./BookingCard.vue";
import {} from "../services/FlightService";
import { store } from "@/store";
import { cancelBooking, getBookings } from "../services/MyBookingsService";
import { getAirportName } from "../utils/AirportCodes";

export default {
  name: "MyBookings",
  components: {
    BookingCard,
  },
  props: {
    msg: String,
  },
  data() {
    return {
      flights: [],
      hotels: [],
      componentKey: 0,
    };
  },
  methods: {
    formatDate(date) {
      return date.toString().split(",").reverse().join("-");
    },
    formatAirport(iataCode) {
      return getAirportName(iataCode);
    },

    forceRerender() {
      this.componentKey += 1;
    },

    async getBookings() {
      const response = await getBookings(store.state.user.user_id);
      this.flights = response.flights;
      this.hotels = response.hotels;
    },

    async deleteBooking(item) {
      console.log(item);
      await cancelBooking(item.id);
      this.forceRerender();
    },
  },
  created() {
    this.getBookings();
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
@import url(https://fonts.googleapis.com/css?family=Open+Sans);

body {
  background: #f2f2f2;
  font-family: "Open Sans", sans-serif;
}

.search {
  width: 30%;
  position: relative;
  display: flex;
  margin: 4em auto;
}

.searchTerm {
  width: 100%;
  border: 3px solid dodgerblue;
  border-right: none;
  padding: 5px;
  height: 31px;
  border-radius: 5px 0 0 5px;
  outline: none;
  color: #9dbfaf;
}

.searchTerm:focus {
  color: dodgerblue;
}

.searchButton {
  width: 80px;
  height: 47px;
  border: 1px solid dodgerblue;
  background: dodgerblue;
  text-align: center;
  color: #fff;
  border-radius: 0 5px 5px 0;
  cursor: pointer;
  font-size: 14px;
}

.card-container {
  display: flex;
  gap: 2em;
  margin: 0 2em;
  justify-content: center;
  flex-wrap: wrap;
}

.card:hover {
  img {
    transform: scale(1.25) rotate(2deg);
  }
}
img {
  height: 100%;
  max-width: 100%;
  vertical-align: middle;
  transition: all ease-in-out 0.4s;
}

@media (max-width: 768px) {
  .card-container {
    flex-direction: column;
  }
}
</style>
