<template>
    <div class="top__clothes-description">
        <p class="top__clothes-description-content">
            昼 : {{ dayDescription }} <br>
            夜 : {{ nightDescription }}
        </p>
    </div>
</template>

<script>
import Icon from '../Weather/Icon.vue'

export default {
  props: ['dayDescription', 'nightDescription'],
  components: {
    Icon
  }
}
</script>

<style>
.top__clothes-description {
    width: 100%;
    height: 90%;
}

.top__clothes-description-content {
    padding: 5%;
    height: 80%;
    overflow: scroll;
    overflow-wrap: break-word;
    margin: 0 auto;
    position: relative;
    text-align: center;
    color: darkblue;
    font-weight: bold;
    font-size: 2.5em;
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