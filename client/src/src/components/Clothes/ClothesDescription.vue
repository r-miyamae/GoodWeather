<template>
    <div class="top__clothes-description-content">
            昼 : {{ dayDescription }} <br>
            夜 : {{ nightDescription }}
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
.top__clothes-description-content {
    margin: 0 auto;
    padding-top: 2%;
    text-align: center;
    font-size: 150%;
    color: #01288d;
    font-weight: bold;
}
</style>