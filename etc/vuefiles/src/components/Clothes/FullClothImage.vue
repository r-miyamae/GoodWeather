<template>
    <div :class="'top__cloth-image-' + gender" class="top__cloth-image">
        <Inner :image="clothes.inner_image" :color="clothes.inner_color"></Inner>
        <Outer :image="clothes.outers_image" :color="clothes.outer_color"></Outer>
        <Bottom :image="clothes.bottom_image" :color="clothes.bottom_color"></Bottom>
    </div>
</template>

<script>
import Inner from './Inner.vue'
import Outer from './Outer.vue'
import Bottom from './Bottom.vue'

export default {
  props: ['gender', 'clothes'],
  components: {
    Inner,
    Outer,
    Bottom
  }
}
</script>

<style>
.top__cloth-image {
    width: 50%;
    height: 90%;
    z-index: 0;
    position: relative;
    -webkit-backface-visibility: hidden;
    backface-visibility: hidden;
}
</style>