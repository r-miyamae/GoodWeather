<template>
    <div class="top__clothes">
        <ClothHeader></ClothHeader>
        <ClothImages :clothes="clothes"></ClothImages>
        <ClothesDescription :description="clothes.clothes_description"></ClothesDescription>
    </div>
</template>

<script>
import ClothHeader from './ClothHeader.vue'
import ClothImages from './ClothImages.vue'
import ClothesDescription from './ClothesDescription.vue'

export default {
  props: ['clothes'],
  components: {
    ClothHeader,
    ClothImages,
    ClothesDescription
  }
}
</script>

<style>
.top__clothes {
    height: 60%;
    position: relative;
    -webkit-transform-style: preserve-3d;
    transform-style: preserve-3d;
    -webkit-transition: .6s;
    transition: .6s;
}
.top__clothes:hover {
    -webkit-transform: rotateY(180deg);
    transform: rotateY(180deg);
}
</style>
