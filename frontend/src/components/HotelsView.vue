<template>
  <div>
    <div class="wrap">
      <div class="search">
        <input
          type="text"
          class="searchTerm"
          placeholder="Where would you like to travel?"
          v-model="destination"
        />
        <input
          type="date"
          class="searchTerm"
          placeholder="When would you arrive?"
          v-model="checkInDate"
        />
        <input
          type="date"
          class="searchTerm"
          placeholder="Util when?"
          v-model="checkOutDate"
        />
        <input
          type="number"
          class="searchTerm"
          placeholder="How many guests?"
          v-model="guests"
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
        @clickBook="book()"
      >
        <template #image>
          <img :src="src()" />
        </template>
        <template #destination>Hotel name: {{ item.name }}</template>
        <template #departureDate
          >Checkin date: {{ formatDate(item.checkInDate) }}
        </template>
        <template #returnDate
          >Checkout date: {{ formatDate(item.checkOutDate) }}</template
        >
        <template #guests>Guests: {{ item.guests }}</template>

        <template #price> {{ item.price }} EUR</template>
      </BookingCard>
    </div>
    <LoginModal v-if="showModal" @close="showModal = false" />
  </div>
</template>

<script>
import { store } from "@/store";
import router from "@/routers";
import BookingCard from "./BookingCard.vue";
import LoginModal from "./LoginModal.vue";
import { getHotels } from "../services/HotelService";
import { getAirportName } from "../utils/AirportCodes";

export default {
  name: "HotelsView",
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
      destination: "MAD",
      checkInDate: null,
      checkOutDate: null,
      guests: null,
    };
  },

  methods: {
    calcId(index) {
      return `id-${index}`;
    },

    book() {
      if (store.state.user.id) {
        router.push({ name: "MyBookings" });
      } else {
        this.showModal = true;
      }
    },

    src() {
      return `https://picsum.photos/id/${
        this.items.length + Math.floor(Math.random() * (180 - 30)) + 30
      }/500/300`;
    },

    formatDate(date) {
      return date.toString().split(",").reverse().join("-");
    },

    formatAirport(iataCode) {
      return getAirportName(iataCode);
    },

    search() {
      this.getSearchResults(
        this.destination,
        this.checkInDate,
        this.checkOutDate,
        this.guests
      );
    },

    async getSearchResults(destination, checkInDate, checkOutDate, guests) {
      if (destination && this.checkInDate && this.checkOutDate && guests) {
        const response = await getHotels(
          destination,
          checkInDate,
          checkOutDate,
          guests
        );
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
