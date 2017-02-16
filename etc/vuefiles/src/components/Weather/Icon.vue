<template>
  <div class="top__weather-icon-wrapper">
    <img class="top__weather-icon" :src="'/assets/weather/' + weatherImageName" :alt="weatherImageName">
    <div class="top__weather-data">
      <TempertureHumidity :temperature="temperature"></TempertureHumidity>
      <RecivedLocation :location="location"></RecivedLocation>
    </div>
  </div>
</template>

<script>
import TempertureHumidity from './TempertureHumidity.vue'
import RecivedLocation from './RecivedLocation.vue'

export default {
  props: ['weatherImageName', 'temperature', 'location'],
  components: {
    TempertureHumidity,
    RecivedLocation
  },
  data () {
    return {
      weatherName: ''
    }
  }
}
</script>

<style>
.top__weather-icon-wrapper {
  height: 90%;
  width: 100%;
  position: absolute;
  top: 0;
  background-color: white;

  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;

  -ms-transform:rotateY(180deg);
  -webkit-transform: rotateY(180deg);
  transform: rotateY(180deg);
}

.top__weather-icon {
  width: 50%;
  height: 50%;
  margin: 25% auto 0 auto;
  display: block;  
}

.top__weather-data {
  width: 100%;
  height: 25%;
  display: flex;
  display: -webkit-flex;
}
</style>