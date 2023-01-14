<template>
  <h1>My trips</h1>
  <div class="card-container" :key="componentKey">
    <BookingCard
      v-for="(item, index) in items"
      :key="index"
      :id="index"
      :inBookings="true"
      @deleteBooking="deleteBooking(item)"
    >
      <template #image>
        <img
          :src="`https://picsum.photos/id/${
            index + Math.floor(Math.random() * (180 - 30)) + 30
          }/500/300`"
        />
      </template>
      <template #destination> {{ item }}</template>
      <template #departureDate> {{ item }} </template>
      <template #returnDate> {{ item }}</template>
      <template #price> {{ item }}</template>
    </BookingCard>
  </div>
</template>

<script>
import BookingCard from "./BookingCard.vue";
import {} from "../services/FlightService";
import { store } from "@/store";
import { cancelBooking, getBookings } from "../services/MyBookingsService";

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
      items: [],
      componentKey: 0,
    };
  },
  methods: {
    forceRerender() {
      this.componentKey += 1;
    },

    async getBookings() {
      console.log(store.state.user);
      const response = await getBookings(store.state.user.user_id);
      this.items = response;
    },

    async deleteBooking(item) {
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
