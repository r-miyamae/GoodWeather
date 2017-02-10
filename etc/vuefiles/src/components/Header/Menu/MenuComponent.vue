<template>
  <div class="header__menu" :class="{ open: !menuToggle , close: menuToggle }">
    <div class="header__menu-content">
      
    </div>
  </div>
</template>

<script>
import Signup from '../../Signup/Signup.vue'

export default {
  components: {
    Signup
  },
  props: ['menuToggle']
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
  background-color: #222222;
  
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
  background-color: #222222;
    
  -webkit-transition: -webkit-transform .6s;
  transition: transform .6s;

  -webkit-transform: translateX(70vw);
  transform: translate(70vw);
}
</style>