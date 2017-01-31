<template>
    <div class="top__weather">
        <Icon :weatherImageName="weather.weather_icon"></Icon>
        <TempertureHumidity :maxTemperature="weather.max_temperature" :minTemperature="weather.min_temperature" :maxHumidity="weather.max_humidity" :minHumidity="weather.min_humidity"></TempertureHumidity>
    </div>
</template>

<script>
import Icon from './Icon.vue'
import TempertureHumidity from './TempertureHumidity.vue'

export default {
  props: ['weather'],
  components: {
    Icon,
    TempertureHumidity
  }
}
</script>

<style>
.top__weather {
    width: 100%;
    height: 30%;
    display: flex;
}
</style>