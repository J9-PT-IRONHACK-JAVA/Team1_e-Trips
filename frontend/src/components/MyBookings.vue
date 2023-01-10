<template>
  <img src="../assets/paotaxidi.png" alt="logo" />
  <h1>My trips</h1>
  <div class="card-container">
    <BookingCard
      v-for="(item, index) in items"
      :key="index"
      :id="index"
      :inBookings="true"
    >
      <template #image>
        <img
          :src="`https://picsum.photos/id/${
            index + Math.floor(Math.random() * (180 - 30)) + 30
          }/500/300`"
        />
      </template>
      <template #destination> {{ item.destination }}</template>
      <template #departureDate> {{ item.departureDate }} </template>
      <template #returnDate> {{ item.returnDate }}</template>
      <template #price> {{ item.price }}</template>
    </BookingCard>
  </div>
</template>

<script>
import BookingCard from "./BookingCard.vue";
export default {
  name: "DashboardView",
  components: {
    BookingCard,
  },
  props: {
    msg: String,
  },
  data() {
    return {
      items: [
        {
          index: 1,
          destination: "New York",
          departureDate: "03/03/2023",
          returnDate: "23/03/2023",
          price: "397EUR",
        },
        {
          index: 2,
          destination: "Madrid",
          departureDate: "03/03/2023",
          returnDate: "23/03/2023",
          price: "37EUR",
        },
        {
          index: 3,
          destination: "London",
          departureDate: "03/03/2023",
          returnDate: "23/03/2023",
          price: "37EUR",
        },
        {
          index: 4,
          destination: "Athens",
          departureDate: "03/03/2023",
          returnDate: "23/03/2023",
          price: "97EUR",
        },
        {
          index: 5,
          destination: "Valencia",
          departureDate: "03/03/2023",
          returnDate: "23/03/2023",
          price: "97EUR",
        },
        {
          index: 6,
          destination: "Mykonos",
          departureDate: "03/03/2023",
          returnDate: "23/03/2023",
          price: "97EUR",
        },
        {
          index: 7,
          destination: "Rome",
          departureDate: "03/03/2023",
          returnDate: "23/03/2023",
          price: "97EUR",
        },
        {
          index: 8,
          destination: "Paris",
          departureDate: "03/03/2023",
          returnDate: "23/03/2023",
          price: "97EUR",
        },
      ],
    };
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
