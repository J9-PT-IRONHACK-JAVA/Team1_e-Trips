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
import { store } from "@/store";
import LoginModal from "./LoginModal.vue";

export default {
  name: "NavBar",
  props: {
    msg: String,
  },
  components: {
    LoginModal,
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
      router.push({ name: "HotelsView" });
    },
    goToBookings() {
      console.log(store.state);
      if (store.state.user.email) {
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
  margin: 0 5em;
  .right {
    display: flex;
    justify-content: space-around;
    width: 35%;
    font-weight: bold;
    margin-top: 3em;
  }

  .navItem {
    cursor: pointer;
    &:hover,
    &:active {
      color: dodgerblue;
    }
  }
}
</style>
