<template>
    <div class="top__cloth-image-inner">
        <img class="top__cloth-image-inner-surface" :src="'/assets/clothes/' + image">
        <div class="top__cloth-image-inner-foundation" :style="{backgroundColor: color}"></div>
    </div>
</template>

<script>
export default {
  props: ['image', 'color']
}
</script>

<style>
.top__cloth-image-inner {
    position: relative;
    height: 45%;
    z-index: 10;
}

.top__cloth-image-inner-surface {
    position: absolute;
    width: 100%;
    height: 100%;
    z-index: 20;
}

.top__cloth-image-inner-foundation {
    position: absolute;
    width: 100%;
    height: 100%;
    z-index: 15;
    background-color: #ffffff;  /*clothColor変数を使って変更される*/
}
</style>