<template>
    <div class="top__weather-tempertureHumidity">
        <Temperture :temperature="temperature"></Temperture>
    </div>
</template>

<script>
import Temperture from './Temperture.vue'

export default {
  props: ['temperature'],
  components: {
    Temperture
  }
}
</script>

<style>
.top__weather-tempertureHumidity {
    height: 100%;
    width: 50%;
}
</style>