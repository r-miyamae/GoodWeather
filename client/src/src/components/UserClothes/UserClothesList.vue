<template>
  <div class="clothes">
    <h3 class="clothes__caption">服一覧</h3>
    <div class="clothes__list">
      <div v-for="cloth in clothes" class="clothes__list-cloth">
        <img class="clothes__list-cloth-img" :src="cloth.clothIcon">
        <div class="clothes__list-cloth-foundation" :style="{backgroundColor: cloth.clothColor}"></div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  created: function () {
    var url = '/api/v1/user/signin'

    axios.get(url)
    .then(function (response) {
    })
    .catch(function (error) {
      console.log(error)
      window.location = '/#/signin'
    })

    var self = this
    url = '/api/v1/user/clothes'

    axios.get(url)
    .then(function (response) {
      self.clothes = response.data
      self.clothes.map(function (cloth) {
        cloth.clothIcon = '/assets/clothes/' + cloth.clothIcon
      })
    })
  },
  data () {
    return {
      clothes: {}
    }
  }
}
</script>

<style>
.clothes {
  width: 100%;
  margin-bottom: 5%;
}

.clothes__caption {
  font-size: 4em;
  color: #333333;
  margin: 4%;
  margin-left: 5%;
  border-bottom: 4px solid #333333
}

.clothes__list {
  display: flex;
  flex-wrap: wrap;
  width: 96%;
  margin: 0 auto;
}

.clothes__list-cloth {
  margin: 10px 2%;
  width: 46%;
  height: 400px;
  position: relative;
}

.clothes__list-cloth-img {
  width: 100%;
  height: 100%;
  border-radius: 30px;
  position: absolute;
  z-index: 40;
}

.clothes__list-cloth-foundation {
  width: 100%;
  height: 100%;
  border-radius: 30px;
  position: absolute;
  z-index: 30;
}
</style>