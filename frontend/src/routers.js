import DashboardView from "./components/DashboardView";
import MyBookings from "./components/MyBookings";
import { createRouter, createWebHashHistory } from "vue-router";

const routes = [
  {
    name: "DashboardView",
    component: DashboardView,
    path: "/",
  },
  {
    name: "MyBookings",
    component: MyBookings,
    path: "/bookings",
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
