<template>
    <div class="top__clothes-image">
        <FullClothImage gender="MENS" :clothes="clothes.man_clothes"></FullClothImage>
        <FullClothImage gender="WOMENS" :clothes="clothes.woman_clothes"></FullClothImage>
        <span class="top__clothes-image-info">＼</span>
    </div>
</template>

<script>
import FullClothImage from './FullClothImage.vue'
export default {
  created: function () {
  },
  props: ['clothes'],
  components: {
    FullClothImage
  }
}
</script>

<style>
.top__clothes-image {
    width: 100%;
    height: 100%;
    display: flex;
    position: absolute;
    -webkit-backface-visibility: hidden;
    backface-visibility: hidden;
}

.top__clothes-image-info {
    z-index: 100;
    position: absolute;
    right: 0;
    top: 0%;
    color: white;
    font-weight: 900;
    font-size: 3em;
}
</style>