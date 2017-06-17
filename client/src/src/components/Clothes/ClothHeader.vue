<template>
    <div class="top__cloth-header" v-if="gender === '' || gender === undefined">
        <ClothHeaderParts gender="MENS"></ClothHeaderParts>
        <ClothHeaderParts gender="WOMENS"></ClothHeaderParts>
    </div>
    <div class="top__cloth-header-full" v-else="">
        <ClothHeaderParts :gender="gender"></ClothHeaderParts>
    </div>
</template>

<script>
import ClothHeaderParts from './ClothHeaderParts.vue'

export default {
  props: ['gender'],
  components: {
    ClothHeaderParts
  }
}
</script>

<style>
.top__cloth-header {
    height: 10%;
    display: flex;
}

.top__cloth-header-full div {
    width: 100%;
}
</style>