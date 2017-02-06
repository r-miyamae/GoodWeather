<template>
    <img class="top__weather-icon" :src="'/assets/weather/' + weatherImageName" :alt="weatherName">
</template>

<script>
export default {
  props: ['weatherImageName'],
  data () {
    return {
      weatherName: ''
    }
  }
}
</script>

<style>
.top__weather-icon {
    height: 100%;
    margin-left: 35%;
}
</style>