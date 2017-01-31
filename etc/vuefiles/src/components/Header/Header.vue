<template>
    <header class="header">
        <HeaderBland></HeaderBland>
        <HambugerMenu></HambugerMenu>
    </header>
</template>

<script>
import HeaderBland from './HeaderBland.vue'
import HambugerMenu from './HambugerMenu.vue'

export default {
  components: {
    HeaderBland,
    HambugerMenu
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