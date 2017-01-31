<template>
    <div class="top__cloth-image-bottom">
        <img class="top__cloth-image-bottom-surface" :src="'/assets/clothes/' + image">
        <div class="top__cloth-image-bottom-foundation":style="{backgroundColor: color}"></div>
    </div>
</template>

<script>
export default {
  props: ['image', 'color']
}
</script>

<style>
.top__cloth-image-bottom {
    position: relative;
    height: 55%;
    z-index: 10;
}

.top__cloth-image-bottom-surface {
    position: absolute;
    width: 100%;
    height: 100%;
    z-index: 20;
}

.top__cloth-image-bottom-foundation {
    position: absolute;
    width: 100%;
    height: 100%;
    z-index: 15;
    background-color: #ffffff;  /*clothColor変数を使って変更される*/
}
</style>