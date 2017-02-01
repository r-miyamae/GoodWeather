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
    width: 30%;
    height: 50%;
    margin-top: 10%;
}
</style>