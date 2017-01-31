<template>
    <div class="top__weather-temperture">
        <span class="top__weather-temperture-max">{{ maxTemperature }} ℃</span><br>
        /
        <span class="top__weather-temperture-min">{{ minTemperature }} ℃</span>
    </div>
</template>

<script>
export default {
  props: ['maxTemperature', 'minTemperature']
}
</script>

<style>
.top__weather-temperture {
    width: 70%;
    height: 50%;
    margin: 0 auto;
    font-size: 300%;
    text-align: center;
}

.top__weather-temperture-max {
    color: darkred;
}

.top__weather-temperture-min {
    color: darkblue;
}
</style>