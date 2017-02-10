<template>
    <header class="header">
        <HeaderBland></HeaderBland>
        <HambugerMenu v-on:changeToggle="changeToggle"></HambugerMenu>
        <MenuComponent :menuToggle="menuToggle"></MenuComponent>
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
      menuToggle: 'first data'
    }
  },
  methods: {
    changeToggle: function (toggle) {
      if (!toggle) {
        this.menuToggle = true
      } else {
        this.menuToggle = false
      }
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