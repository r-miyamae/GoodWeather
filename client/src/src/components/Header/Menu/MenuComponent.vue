<template>
  <div class="header__menu" :class="{ open: !menuToggle , close: menuToggle }">
    <div class="header__menu-content">
      <div class="header__menu-content-buttons">
        <router-link to="/signup" class="header__menu-content-button header__menu-content-signup" v-on:click.native="menuForNonSignIn">Sign up</router-link>
        <router-link to="/signin" class="header__menu-content-button header__menu-content-signin" v-on:click.native="menuForNonSignIn">Sign in</router-link>
      </div>
      <ul class="header__menu-content-linkList">
        <router-link to="/" class="header__menu-content-top" tag="li" v-on:click.native="menuForNonSignIn">今日の服装</router-link>
        <router-link to="/clothes" class="header__menu-content-top" tag="li" v-on:click.native="menuForSignIn">服一覧</router-link>
        <router-link to="/register" class="header__menu-content-top" tag="li" v-on:click.native="menuForSignIn">新しい服登録</router-link>
      </ul>
      <button class="header__menu-content-logout-button" type="button" @click="signOut">Sign Out</button>
    </div>
  </div>
</template>

<script>
import Signup from '../../Signup/Signup.vue'
import axios from 'axios'

export default {
  components: {
    Signup
  },
  props: ['menuToggle'],
  methods: {
    menuForNonSignIn: function () {
      this.resetToggle()
    },
    menuForSignIn: function () {
      this.loginCheck()
      this.resetToggle()
    },
    signOut: function () {
      var url = '/api/v1/user/logout'
      var self = this

      axios.post(url)
        .then(function (response) {
          self.resetToggle()
          window.location = '/#/'
          window.location.reload()
        })
        .catch(function (error) {
          self.resetToggle()
          console.log(error)
        })
    },
    resetToggle: function () {
      this.$emit('resetToggle')
    },
    loginCheck: function () {
      var url = '/api/v1/user/signin'

      axios.get(url)
        .then(function (response) {
        })
        .catch(function (error) {
          console.log(error)
          window.location = '/#/signin'
        })
    }
  }
}
</script>

<style>
.header__menu-content {
  position: relative;
  margin: 0 auto;
  padding: 0;
  width: 90%;
  height: 90%;
}

.header__menu {
  width: 40vw;
  height: 100vh;
  position: fixed;
  top: 0;
  right: -40vw;
  z-index: 990;
  background-color: #888888;
}

.open {
  -webkit-transition: -webkit-transform .6s;
  transition: transform .6s;

  -webkit-transform: translateX(-40vw);
  transform: translate(-40vw);
}

.close {
  -webkit-transition: -webkit-transform .6s;
  transition: transform .6s;

  -webkit-transform: translateX(40vw);
  transform: translate(40vw);
}

.header__menu-content-buttons {
  width: 100%;
  height: 6%;
  margin-top: 20%;
  display: flex;
}

.header__menu-content-button {
  width: 50%;
  padding-top: 1%;
  font-size: 150%;
  text-decoration: none;
  text-align: center;
}

.header__menu-content-signup {
  color: #ffffff;
  background-color: #ffa500;
}

.header__menu-content-signin {
  color: #444444;
  background-color: #ffffff;
}

.header__menu-content-linkList {
  margin: 3% 0 0 0;
  padding: 0;
}

.header__menu-content-linkList li {
  list-style: none;
  font-size: 150%;
  padding: 5% 0 3% 0;
  border-bottom: 3px solid #222222;
  cursor: pointer;
}

.header__menu-content-logout-button {
  position: absolute;
  right: 5%;
  bottom: 10%;
  padding: 3% 5%;
  border: 0;
  color: #ffffff;
  background-color: #444444;
  font-size: 150%;
}
</style>