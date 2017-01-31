<template>
    <div class="top__selectLocation">
        {{ location }}
    </div>
</template>

<script>
export default {
  props: ['location']
}
</script>

<style>
.top__selectLocation {
    width: 100%;
    height: 8%;
    padding-top: 5%;
    display: inline-block;
    font-size: 6em;
    text-align: center;
    vertical-align: middle;
    background-color: #01288d;
    color: white;
}
</style>