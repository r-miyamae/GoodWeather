<template>
  <div v-if="manOuterData != '' || isDisplay == false" class="top__clothes-outer-toggle-button" @click="outerToggle">
    <span v-if="isDisplay"><b>インナー</b></span><span v-else><b>アウター</b></span>を見る 
  </div>
</template>

<script>
  export default {
    props: ['manOuterData'],
    methods: {
      outerToggle: function () {
        if (this.isDisplay) {
          this.isDisplay = false
          this.$emit('hideOuter')
        } else {
          this.isDisplay = true
          this.$emit('indicateOuter')
        }
      }
    },
    data () {
      return {
        isDisplay: true
      }
    }
  }
</script>

<style>
.top__clothes-outer-toggle-button {
  background-color: deepskyblue;
  width: 100%;
  height: 10%;
  position: relative;
  top: -10%;
  font-size: 4em;
  color: #ffffff;
  text-align: center;
}
</style>