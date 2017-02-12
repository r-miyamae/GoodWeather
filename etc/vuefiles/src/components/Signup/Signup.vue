<template>
  <div class="signup">
    <h3 class="signup__caption">Sign Up</h3>
    <InputData></InputData>
  </div>
</template>

<script>
import InputData from './InputData.vue'

export default {
  components: {
    InputData
  }
}
</script>

<style>
.signup {
  width: 100%;
  height: 100%;
}

.signup__caption {
  font-size: 4em;
  color: #333333;
  margin: 4%;
  margin-left: 5%;
  border-bottom: 4px solid #333333
}
</style>