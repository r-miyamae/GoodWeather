<template>
  <div id="app">
    <Header></Header>
    <router-view></router-view>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from './components/Header/Header.vue'
import Footer from './components/Footer/Footer.vue'

export default {
  components: {
    Header,
    Footer
  }
}
</script>

<style>
#app {
  height: 100%;
  font-family: sans-serif;
}
</style>
