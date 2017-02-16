<template>
    <div class="top__weather-tempertureHumidity">
        <Temperture :maxTemperature="maxTemperature" :minTemperature="minTemperature"></Temperture>
        <Humidity :humidity="humidity"></Humidity>
    </div>
</template>

<script>
import Temperture from './Temperture.vue'
import Humidity from './Humidity.vue'

export default {
  props: ['maxTemperature', 'minTemperature', 'humidity'],
  components: {
    Temperture,
    Humidity
  }
}
</script>

<style>
.top__weather-tempertureHumidity {
    height: 60%;
    width: 40%;
    margin-top: 5%;
    float: right;
}
</style>