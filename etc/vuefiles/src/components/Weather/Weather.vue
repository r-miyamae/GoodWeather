<template>
    <div class="top__weather">
        <Icon :weatherImageName="weather.weather_icon"></Icon>
        <TempertureHumidity :maxTemperature="weather.max_temperature" :minTemperature="weather.min_temperature" :humidity="weather.humidity"></TempertureHumidity>
        <RecivedLocation :location="location"></RecivedLocation>
    </div>
</template>

<script>
import Icon from './Icon.vue'
import TempertureHumidity from './TempertureHumidity.vue'
import RecivedLocation from './RecivedLocation.vue'

export default {
  props: ['weather', 'location'],
  components: {
    Icon,
    TempertureHumidity,
    RecivedLocation
  }
}
</script>

<style>
.top__weather {
    width: 100%;
    height: 20%;
}
</style>