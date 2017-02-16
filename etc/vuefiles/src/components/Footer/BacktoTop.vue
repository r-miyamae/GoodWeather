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
    height: 20%;
    padding-top: 1%;
    font-size: 4em;
    text-align: center;
    background-color: lightskyblue;
    color: white;
}
</style>