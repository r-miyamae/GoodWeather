<template>
    <div class="top__footer-backtotop" @click="backToTop">
      ページトップに戻る
    </div>
</template>

<script>
export default {
  methods: {
    backToTop: event => {
      window.scrollTo(0, 0)
    }
  }
}
</script>

<style>
.top__footer-backtotop {
    width: 100%;
    height: 30%;
    font-size: 150%;
    text-align: center;
    background-color: #44ccff;
    color: #ffffff;
    cursor: pointer;
}
</style>