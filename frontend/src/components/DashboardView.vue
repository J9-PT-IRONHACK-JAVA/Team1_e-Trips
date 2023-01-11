<template>
  <div>
    <img src="../assets/paotaxidi.png" alt="logo" />
    <div class="wrap">
      <div class="search">
        <input
          type="text"
          class="searchTerm"
          placeholder="Where would you like to travel?"
          v-model="origin"
        />
        <input
          type="text"
          class="searchTerm"
          placeholder="When would you like to travel?"
          v-model="date"
        />
        <button type="submit" class="searchButton" @click="search()">
          Search
        </button>
      </div>
    </div>
    <div class="card-container">
      <BookingCard
        v-for="(item, index) in items"
        :key="index"
        :id="calcId(index)"
        :inBookings="false"
      >
        <template #image>
          <img :src="c_src(index)" />
        </template>
        <template #destination>
          {{
            formatAirport(item.destination) + " - " + item.destination
          }}</template
        >
        <template #departureDate
          >Departure date: {{ item.departureDate }}
        </template>
        <template #returnDate>Return date: {{ item.returnDate }}</template>
        <template #price> {{ item.price }} EUR</template>
      </BookingCard>
    </div>
    <LoginModal v-if="showModal" @close="showModal = false" />
  </div>
</template>

<script>
import BookingCard from "./BookingCard.vue";
import LoginModal from "./LoginModal.vue";
import {
  getFlights,
  getFlightsByOrigin,
  getFlightsByDate,
} from "../services/FlightService";
import { getAirportName } from "../utils/AirportCodes";

export default {
  name: "DashboardView",
  components: {
    BookingCard,
    LoginModal,
  },
  props: {
    msg: String,
  },
  data() {
    return {
      showModal: false,
      items: [],
      origin: "MAD",
      date: null,
    };
  },

  methods: {
    calcId(index) {
      return `id-${index}`;
    },

    c_src(index) {
      return `https://picsum.photos/id/${
        index + Math.floor(Math.random() * (180 - 30)) + 30
      }/500/300`;
    },
    /*
    formatDate(date) {
      return date.split("").reverse().join("").toString().replaceAll(",", "-");
    },*/

    formatAirport(iataCode) {
      return getAirportName(iataCode);
    },

    search() {
      this.getSearchResults(this.origin, this.date);
    },

    async getSearchResults(origin, departureDate) {
      if (origin && departureDate) {
        const response = await getFlights(origin, departureDate);
        this.items = response;
      } else if (departureDate) {
        const response = await getFlightsByDate(departureDate);
        this.items = response;
      } else if (origin) {
        const response = await getFlightsByOrigin(origin);
        this.items = response;
      }
    },
  },
  created() {
    this.getSearchResults("MAD", null);
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
