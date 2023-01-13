import { createApp } from "vue";
import { createStore } from "vuex";
import App from "../App.vue";

class User {
  constructor() {
    this.id = null;
    this.email = "";
    this.username = "";
    this.password = "";
    this.roles = "";
  }
}
// Create a new store instance.
export const store = createStore({
  state: {
    user: new User(),
  },
  mutations: {
    SET_USER(state, user) {
      state.user = { ...state.user, ...user };
    },
  },
});

const app = createApp(App);

// Install the store instance as a plugin
app.use(store);
