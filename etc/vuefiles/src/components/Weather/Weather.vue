<template>
    <div class="top__weather">
        <ClothesDescription :dayDescription="dayDescription" :nightDescription="nightDescription"></ClothesDescription>
    </div>
</template>

<script>
import ClothesDescription from '../Clothes/ClothesDescription.vue'

export default {
  props: ['dayDescription', 'nightDescription'],
  components: {
    ClothesDescription
  }
}
</script>

<style>
.top__weather {
    width: 100%;
    height: 21%;
}
</style>