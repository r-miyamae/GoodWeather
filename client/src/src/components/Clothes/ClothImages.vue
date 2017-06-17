<template>
    <div class="top__clothes-image" v-if="gender === '' || gender === undefined">
        <FullClothImage gender="MENS" :clothes="clothes.man_clothes"></FullClothImage>
        <FullClothImage gender="WOMENS" :clothes="clothes.woman_clothes"></FullClothImage>
        <span class="top__clothes-image-info"><img src="/assets/return.png" alt="return"></span>
    </div>
    <div class="top__clothes-image-full" v-else="">
        <FullClothImage :gender="gender" :clothes="clothes.man_clothes"></FullClothImage>
        <span class="top__clothes-image-info">天気→</span>
    </div>
</template>

<script>
import FullClothImage from './FullClothImage.vue'
export default {
  created: function () {
  },
  props: ['clothes', 'gender'],
  components: {
    FullClothImage
  }
}
</script>

<style>
.top__clothes-image {
    width: 100%;
    height: 80%;
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

.top__clothes-image-info img {
    width: 15%;
    display: block;
    position: relative;
    left: 85%;
}

.top__clothes-image-full {
    width: 100%;
    height: 80%;
    margin: 0 auto;
    -ms-transform:rotateY(180deg);
    -webkit-transform: rotateY(180deg);
    transform: rotateY(180deg);
}

.top__clothes-image-full > div {
    width: 70%;
    margin: 0 auto;
}
</style>