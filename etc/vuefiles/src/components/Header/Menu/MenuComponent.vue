<template>
  <div class="header__menu" :class="{ open: !menuToggle , close: menuToggle }">
    <div class="header__menu-content">
      <div class="header__menu-content-buttons">
        <router-link to="/signup" class="header__menu-content-signup" v-on:click.native="menuForNonSignIn">Sign up</router-link>
        <router-link to="/signin" class="header__menu-content-signin" v-on:click.native="menuForNonSignIn">Sign in</router-link>
      </div>
      <ul class="header__menu-content-linkList">
        <router-link to="/" class="header__menu-content-top" tag="li" v-on:click.native="menuForNonSignIn">Clothes &amp; Weather</router-link>
        <router-link to="/clothes" class="header__menu-content-top" tag="li" v-on:click.native="menuForSignIn">UserClothes</router-link>
        <router-link to="/register" class="header__menu-content-top" tag="li" v-on:click.native="menuForSignIn">UserClothRegist</router-link>
      </ul>
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
  top: 3em;
  font-size: 2em;
  margin: 0 auto;
  padding: 0;
  width: 90%;
  height: 90%;
}

.open {
  width: 70vw;
  height: 100vh;
  position: fixed;
  top: 0;
  right: -70vw;
  z-index: 990;
  background-color: #888888;
  
  -webkit-transition: -webkit-transform .6s;
  transition: transform .6s;

  -webkit-transform: translateX(-70vw);
  transform: translate(-70vw);
}
.close {
  width: 70vw;
  height: 100vh;
  position: fixed;
  top: 0;
  right: -70vw;
  z-index: 990;
  background-color: #888888;
    
  -webkit-transition: -webkit-transform .6s;
  transition: transform .6s;

  -webkit-transform: translateX(70vw);
  transform: translate(70vw);
}

.header__menu-content-buttons {
  width: 100%;
  height: 8%;
  display: flex;
}

.header__menu-content-signup {
  margin: 0 3%;
  padding: 10px;
  padding-top: 3%;
  width: 50%;
  border-radius: 10px;
  text-decoration: none;
  text-align: center;
  color: #ffffff;
  background-color: #ffa500;
}

.header__menu-content-signin {
  margin: 0 3%;  
  padding: 10px;
  padding-top: 3%;
  width: 50%;
  border-radius: 10px;  
  text-decoration: none;
  text-align: center;
  color: #444444;
  background-color: #ffffff;
}

.header__menu-content-linkList {
  margin: 0;
  padding: 0;
  padding-top: 10%;
}

.header__menu-content-linkList li {
  list-style: none;
  padding: 5% 0;
  border-bottom: 3px solid #222222;
}
</style>