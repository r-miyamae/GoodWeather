<template>
    <img class="top__weather-icon" :src="'/assets/img/weather/' + weatherImageName" :alt="weatherName">
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
    width: 40%;
    height: 80%;
    margin-left: 30%;
    margin-top: 5%;
}
</style>