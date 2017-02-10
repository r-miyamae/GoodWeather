// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'

Vue.use(VueRouter)

import Top from './components/Top.vue'
import SignUp from './components/Signup/Signup.vue'
import SignIn from './components/Signin/Signin.vue'

const router = new VueRouter({
  routes: [
    {path: '/', component: Top},
    {path: '/top', component: Top},
    {path: '/signup', component: SignUp},
    {path: '/signin', component: SignIn}
  ]
})

router.beforeEach((route, redirect, next) => {
  /* your route hooks */
  next()
})

router.afterEach(function (transition) {
  /* your route hooks */
})

/* eslint-disable no-new */
new Vue({
  router,
  el: '#app',
  template: '<App/>',
  components: { App }
})
