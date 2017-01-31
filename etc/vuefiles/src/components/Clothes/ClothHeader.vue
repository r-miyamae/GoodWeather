<template>
    <div class="top__cloth-header">
        <ClothHeaderParts gender="MENS"></ClothHeaderParts>
        <ClothHeaderParts gender="WOMENS"></ClothHeaderParts>           
    </div>
</template>

<script>
import ClothHeaderParts from './ClothHeaderParts.vue'

export default {
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
</style>