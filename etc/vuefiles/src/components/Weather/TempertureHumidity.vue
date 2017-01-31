<template>
    <div class="top__weather-tempertureHumidity">
        <Temperture :maxTemperature="maxTemperature" :minTemperature="minTemperature"></Temperture>
        <Humidity :maxHumidity="maxHumidity" :minHumidity="minHumidity"></Humidity>
    </div>
</template>

<script>
import Temperture from './Temperture.vue'
import Humidity from './Humidity.vue'

export default {
  props: ['maxTemperature', 'minTemperature', 'maxHumidity', 'minHumidity'],
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