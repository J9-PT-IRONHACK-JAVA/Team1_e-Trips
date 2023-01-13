import { computed } from 'vue'; import { computed } from 'vue';
<template>
  <div>
    <div class="card">
      <div class="card_image">
        <slot name="image"></slot>
      </div>
      <div class="card_content">
        <h2 class="card_title"><slot name="destination"></slot></h2>
        <p class="card_text">
          <slot name="departureDate"></slot>
        </p>
        <p class="card_text">
          <slot name="returnDate"></slot>
        </p>
        <h2 class="card_title"><slot name="price"></slot></h2>

        <button class="btn card_btn" v-show="showBook" @click="action()">
          Book
        </button>
        <button class="btn card_btn" v-show="showEdit" @click="edit()">
          Edit
        </button>
        <button class="btn card_btn" v-show="showEdit" @click="erase()">
          Delete
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { computed } from "vue";

export default {
  props: ["id", "inBookings"],
  emits: ["clickBook"],
  setup(props, { emit }) {
    // setup() receives props as the first argument.

    const showBook = computed(() => {
      return !props.inBookings;
    });

    const showEdit = computed(() => {
      return props.inBookings;
    });

    const action = () => {
      emit("clickBook");
      return props.id;
    };
    const edit = () => {
      //if logged in as admin patch
      return props.id;
    };
    const erase = () => {
      //delete
      return props.id;
    };
    return {
      showEdit,
      showBook,
      action,
      erase,
      edit,
    };
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
/* Font */
@import url("https://fonts.googleapis.com/css?family=Quicksand:400,700");

h1 {
  font-size: 24px;
  font-weight: 400;
  text-align: center;
}

.btn {
  color: #ffffff;
  padding: 0.8rem;
  font-size: 14px;
  text-transform: uppercase;
  border-radius: 4px;
  font-weight: 400;
  display: block;
  width: 100%;
  cursor: pointer;
  border: 3px solid rgba(255, 255, 255, 0.2);
  background: transparent;
  margin-top: 1em;
}

.btn:hover {
  background-color: rgba(255, 255, 255, 0.12);
}

.cards {
  display: flex;
  flex-wrap: wrap;
  list-style: none;
  margin: 0;
  padding: 0;
}

.cards_item {
  display: flex;
  padding: 1rem;
}

.card {
  background-color: white;
  border-radius: 0.25rem;
  box-shadow: 0 20px 40px -14px rgba(0, 0, 0, 0.25);
  display: flex;
  flex-direction: column;
  overflow: hidden;
  max-width: 450px;
  min-width: 300px;
  flex-basis: 33.333333%;
}

.card_content {
  padding: 1rem;
  background: linear-gradient(to bottom left, #ef8d9c 40%, #ffc39e 100%);
  z-index: 10;
}

.card_title {
  color: #ffffff;
  font-size: 1.1rem;
  font-weight: 700;
  letter-spacing: 1px;
  text-transform: capitalize;
  margin: 0px;
}

.card_text {
  color: #ffffff;
  font-size: 0.875rem;
  line-height: 1.5;
  margin-bottom: 1.25rem;
  font-weight: 400;
}
.made_by {
  font-weight: 400;
  font-size: 13px;
  margin-top: 35px;
  text-align: center;
}
</style>
