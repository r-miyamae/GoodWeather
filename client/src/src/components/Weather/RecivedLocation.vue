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
    height: 100%;
    width: 50%;
    padding-top: 5%;
    color: #01288d;
    overflow: auto;
    overflow-wrap: break-word;
    font-size: 4em;
    text-align: center
}
</style>