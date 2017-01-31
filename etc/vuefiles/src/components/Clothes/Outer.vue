<template>
    <div class="top__cloth-image-outer">
        <img class="top__cloth-image-outer-surface" :src="'/assets/clothes/' + image">
        <div class="top__cloth-image-outer-foundation" :style="{backgroundColor: color}"></div>
    </div>
</template>

<script>
export default {
  props: ['image', 'color']
}
</script>

<style>
.top__cloth-image-outer {
    height: 45%;
    width: 100%;
    position: absolute;
    top: 0%;
    left: 0%;
    z-index: 30;
}

.top__cloth-image-outer-surface {
    width: 100%;
    height: 100%;
    position: absolute;
    z-index: 40;
}

.top__cloth-image-outer-foundation {
    width: 100%;
    height: 100%;
    position: absolute;
    z-index: 35;
    background-color: rgba(255, 255, 255, 0.0);  /*clothColor変数を使って変更される*/
}
</style>