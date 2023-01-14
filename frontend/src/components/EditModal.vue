<template>
  <!--Login Modal-->
  <div class="modal-container" @click="$emit('close')">
    <div class="login-box" @click.stop="">
      <h2>Login</h2>
      <form>
        <div class="user-box">
          <input type="email" v-model="email" name="" required />
          <label>Email</label>
        </div>
        <div class="user-box">
          <input type="text" v-model="name" name="" required />
          <label>Username</label>
        </div>
        <div class="user-box">
          <input type="password" v-model="password" name="" required />
          <label>Password</label>
        </div>
        <a @click="action">
          <span></span>
          <span></span>
          <span></span>
          <span></span>
          Submit
        </a>
      </form>
    </div>
  </div>
</template>

<script>
import { ref } from "vue";
import { createUser } from "../services/UserService";
import { store } from "@/store";
import router from "@/routers";
import {
  createHotelBooking,
  createFlightBooking,
} from "../services/MyBookingsService";

export default {
  emits: ["close"],
  props: {
    booking: {
      default: null,
    },
  },

  setup(props, { emit }) {
    const name = ref("");
    const password = ref("");
    const email = ref("");

    const action = async () => {
      const response = await createUser(
        email.value,
        name.value,
        password.value
      );
      emit("close");
      router.push({ name: "MyBookings" });
      store.commit("SET_USER", response);
      if (props.booking.origin) {
        await createFlightBooking(
          props.booking.origin,
          props.booking.destination,
          props.booking.departureDate,
          props.booking.returnDate,
          props.booking.price
        );
      } else if (props.booking.guests) {
        await createHotelBooking(
          props.booking.name,
          props.booking.checkInDate,
          props.booking.checkOutDate,
          props.booking.guests,
          props.booking.price
        );
      }
    };

    return {
      name,
      password,
      email,
      action,
    };
  },
};
</script>

<style scoped>
html {
  height: 100%;
}
body {
  margin: 0;
  padding: 0;
  font-family: sans-serif;
  background: linear-gradient(#141e30, #243b55);
}
.modal-container {
  position: fixed;
  top: 0;
  left: 0;
  height: 100vh;
  width: 100vw;
  background: rgba(0, 0, 0, 0.5);
  z-index: 11;
}
.login-box {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 400px;
  padding: 40px;
  transform: translate(-50%, -50%);
  background: cornflowerblue;
  box-sizing: border-box;
  border-radius: 10px;
  z-index: 12;
}

.login-box h2 {
  margin: 0 0 30px;
  padding: 0;
  color: #fff;
  text-align: center;
}

.login-box .user-box {
  position: relative;
}

.login-box .user-box input {
  width: 100%;
  padding: 10px 0;
  font-size: 16px;
  color: #fff;
  margin-bottom: 30px;
  border: none;
  border-bottom: 1px solid #fff;
  outline: none;
  background: transparent;
}
.login-box .user-box label {
  position: absolute;
  top: 0;
  left: 0;
  padding: 10px 0;
  font-size: 16px;
  color: #fff;
  pointer-events: none;
  transition: 0.5s;
}

.login-box .user-box input:focus ~ label,
.login-box .user-box input:valid ~ label {
  top: -20px;
  left: 0;
  color: #03e9f4;
  font-size: 12px;
}

.login-box form a {
  position: relative;
  display: inline-block;
  padding: 10px 20px;
  color: #03e9f4;
  font-size: 16px;
  text-decoration: none;
  text-transform: uppercase;
  overflow: hidden;
  transition: 0.5s;
  margin-top: 40px;
  letter-spacing: 4px;
}

.login-box a:hover {
  background: #03e9f4;
  color: #fff;
  border-radius: 5px;
  box-shadow: 0 0 5px #03e9f4, 0 0 25px #03e9f4, 0 0 50px #03e9f4,
    0 0 100px #03e9f4;
}

.login-box a span {
  position: absolute;
  display: block;
}

.login-box a span:nth-child(1) {
  top: 0;
  left: -100%;
  width: 100%;
  height: 2px;
  background: linear-gradient(90deg, transparent, #03e9f4);
  animation: btn-anim1 1s linear infinite;
}

@keyframes btn-anim1 {
  0% {
    left: -100%;
  }
  50%,
  100% {
    left: 100%;
  }
}

.login-box a span:nth-child(2) {
  top: -100%;
  right: 0;
  width: 2px;
  height: 100%;
  background: linear-gradient(180deg, transparent, #03e9f4);
  animation: btn-anim2 1s linear infinite;
  animation-delay: 0.25s;
}

@keyframes btn-anim2 {
  0% {
    top: -100%;
  }
  50%,
  100% {
    top: 100%;
  }
}

.login-box button span:nth-child(3) {
  bottom: 0;
  right: -100%;
  width: 100%;
  height: 2px;
  background: linear-gradient(270deg, transparent, #03e9f4);
  animation: btn-anim3 1s linear infinite;
  animation-delay: 0.5s;
}

@keyframes btn-anim3 {
  0% {
    right: -100%;
  }
  50%,
  100% {
    right: 100%;
  }
}

.login-box button span:nth-child(4) {
  bottom: -100%;
  left: 0;
  width: 2px;
  height: 100%;
  background: linear-gradient(360deg, transparent, #03e9f4);
  animation: btn-anim4 1s linear infinite;
  animation-delay: 0.75s;
}

@keyframes btn-anim4 {
  0% {
    bottom: -100%;
  }
  50%,
  100% {
    bottom: 100%;
  }
}
</style>
