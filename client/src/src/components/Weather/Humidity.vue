<template>
    <div class="top__weather-humidity">
        <span class="top__weather-humidity-max">{{ humidity }} ％</span><br>
    </div>
</template>

<script>
export default {
  props: ['humidity']
}
</script>

<style>
.top__weather-humidity {
    width: 100%;
    height: 50%;
    vertical-align: middle;
    margin: 0 auto;
    margin-top: 10%;
    font-size: 300%;
    text-align: center;
}

</style>