import DashboardView from "./components/DashboardView";
import MyBookings from "./components/MyBookings";
import HotelsView from "./components/HotelsView";
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
  {
    name: "HotelsView",
    component: HotelsView,
    path: "/hotels",
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
