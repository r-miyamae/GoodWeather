<template>
    <div class="top__clothes-description">
        <p class="top__clothes-description-content">
            {{ description }}
        </p>
        <span class="top__clothes-description-info">／</span>
    </div>
</template>

<script>
export default {
  props: ['description']
}
</script>

<style>
.top__clothes-description {
    position: absolute;
    width: 100%;
    height: 90%;
    z-index: 1000;
    -webkit-backface-visibility: hidden;
    backface-visibility: hidden;

    background-color: lightblue;
    -webkit-transform: rotateY(180deg);
    transform: rotateY(180deg);
}

.top__clothes-description-content {
    width: 80%;
    margin: 0 auto;
    margin-top: 20vh;
    position: relative;
    text-align: center;
    color: darkblue;
    font-weight: bold;
    font-size: 4em;
}

.top__clothes-description-info {
    position: absolute;
    right: 0;
    bottom: 0%;
    color: white;
    font-weight: bold;
    font-size: 3em;
}
</style>