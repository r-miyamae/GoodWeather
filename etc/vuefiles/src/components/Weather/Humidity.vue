<template>
    <div class="top__weather-humidity">
        <span class="top__weather-humidity-max">{{ maxHumidity }} ％</span><br>
        /
        <span class="top__weather-humidity-min">{{ minHumidity }} ％</span>
    </div>
</template>

<script>
export default {
  props: ['maxHumidity', 'minHumidity']
}
</script>

<style>
.top__weather-humidity {
    width: 70%;
    height: 50%;
    vertical-align: middle;
    margin: 0 auto;
    font-size: 300%;
    text-align: center;
}

</style>