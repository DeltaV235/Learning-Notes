import {createMemoryHistory, createRouter, createWebHistory} from "vue-router";
import Home from "../views/Home.vue";
import Summary from "../views/Summary.vue";
import User from "../views/User.vue";
import UserProfile from "../components/UserProfile.vue";
import UserMail from "../components/UserMail.vue";

const routes = [
    {path: "/", component: Home, name: "Home"},
    {path: "/summary", component: Summary, name: "Summary"},
    {path: "/login/:uid", component: () => import("../views/Login.vue")},
    {
        path: "/user/:uid",
        component: User,
        name: "User",
        children: [
            {
                path: "profile",
                name: "UserProfile",
                component: UserProfile,
            },
            {
                path: "mail",
                name: "UserMail",
                component: UserMail,
            }
        ]
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
});

router.beforeEach((to, from, next) => {
    console.log(to, from);
    if (to.name === 'Summary') {
        next('/');
    } else {
        next();
    }
})

export default router;