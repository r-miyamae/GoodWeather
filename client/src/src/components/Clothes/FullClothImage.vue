<template>
    <div :class="'top__cloth-image-' + gender" class="top__cloth-image" v-if="clothes.inner_image !== null && clothes.outer_image !== null && clothes.bottom_image !== null">
        <Inner :image="clothes.inner_image" :color="clothes.inner_color"></Inner>
        <Outer :image="clothes.outer_image" :color="clothes.outer_color"></Outer>
        <Bottom :image="clothes.bottom_image" :color="clothes.bottom_color"></Bottom>
    </div>
    <div class="top__cloth-image-null" v-else="">
        本日の気候に合う服が登録されていないため、服装は表示できません。<router-link to="/register">こちら</router-link>よりあなたの持っている服を登録してください。
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
    height: 100%;
    z-index: 0;
    position: relative;
    -webkit-backface-visibility: hidden;
    backface-visibility: hidden;
}

.top__cloth-image-null {
    width: 90% !important;
    height: 85%;
    font-size: 3em;
    padding-top: 15%;
    margin: 3% 0;
    background-color: lightblue;
}
</style>