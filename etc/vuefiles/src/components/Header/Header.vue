<template>
    <header class="header">
        <HeaderBland></HeaderBland>
        <HambugerMenu :menuActive="menuActive" v-on:changeMenu="changeMenu"></HambugerMenu>
        <MenuComponent :menuToggle="menuToggle" v-on:resetToggle="resetToggle"></MenuComponent>
    </header>
</template>

<script>
import HeaderBland from './HeaderBland.vue'
import HambugerMenu from './HambugerMenu.vue'
import MenuComponent from './Menu/MenuComponent.vue'

export default {
  components: {
    HeaderBland,
    HambugerMenu,
    MenuComponent
  },
  data () {
    return {
      menuToggle: 'first data',
      menuActive: false
    }
  },
  methods: {
    changeMenu: function (toggle) {
      if (!toggle) {
        this.menuActive = true
        this.menuToggle = false
      } else {
        this.menuActive = false
        this.menuToggle = true
      }
    },
    resetToggle: function () {
      this.menuToggle = 'first data'
      this.menuActive = false
    }
  }
}
</script>

<style>
    header {
        margin: 0 auto;
        padding: 1.5em 0;
        width: 100%;
        height: 3em;
        background-color: #01288d;
        color: #ffffff;
        font-size: 2em;
        display: flex;        
    }
</style>