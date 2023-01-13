<template>
  <div class="navbar">
    <div class="left">
      <img src="../assets/paotaxidi.png" alt="logo" />
    </div>
    <div class="right">
      <div class="navItem" @click="goToFlights()">Flights</div>

      <div class="navItem" @click="goToHotels()">Hotels</div>

      <div class="navItem" @click="goToBookings()">My bookings</div>
    </div>
    <LoginModal v-if="showModal" @close="showModal = false" />
  </div>
</template>

<script>
import router from "@/routers";
import store from "@/store";

export default {
  name: "NavBar",
  props: {
    msg: String,
  },
  data() {
    return {
      showModal: false,
    };
  },
  methods: {
    goToFlights() {
      router.push({ name: "DashboardView" });
    },
    goToHotels() {
      router.push({ name: "Hotels" });
    },
    goToBookings() {
      if (store.state.user.id) {
        router.push({ name: "MyBookings" });
      } else {
        this.showModal = true;
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.navbar {
  display: flex;
  justify-content: space-between;
  .right {
    display: flex;
    justify-content: space-around;
    text-decoration: underline;
    width: 35%;
    font-weight: bold;
    margin-top: 3em;
  }

  .navItem {
    cursor: pointer;
    :hover,
    :active {
      color: dodgerblue;
    }
  }
}
</style>
