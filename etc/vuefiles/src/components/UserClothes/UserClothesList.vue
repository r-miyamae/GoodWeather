<template>
  <div class="clothes__list">
  </div>
</template>

<script>
import axios from 'axios'

export default {
  created: function () {
    var url = '/api/v1/user/clothes'

    axios.get(url)
    .then(function (response) {
      console.log(response)
    })
  }
}
</script>