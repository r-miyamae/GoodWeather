<template>
    <div class="top__weather-temperture">
        <span class="top__weather-temperture-max">{{ temperature }} ℃</span><br>
    </div>
</template>

<script>
export default {
  props: ['temperature']
}
</script>

<style>
.top__weather-temperture {
    width: 100%;
    height: 100%;
    padding-top: 10%;
    font-size: 3em;
    text-align: center;
}

.top__weather-temperture-max {
    color: darkred;
}

.top__weather-temperture-min {
    color: darkblue;
}
</style>