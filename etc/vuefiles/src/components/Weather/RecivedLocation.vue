<template>
  <div class="top__weather-user-location">
    {{ location }}
  </div>
</template>

<script>
export default {
  props: ['location']
}
</script>

<style>
.top__weather-user-location {
    height: 20%;
    color: #01288d;
    font-size: 3em;
    padding-top: 2%;
    padding-left: 1em;
    width: 40%;
    float: right;
    text-align: center
}
</style>