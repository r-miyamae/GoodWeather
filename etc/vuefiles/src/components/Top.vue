<template>
  <div class="top">
    <Clothes :clothes="dataForTop.clothes"></Clothes>
    <Weather :weather="dataForTop.weather"></Weather>
    <SelectLocation :location="dataForTop.user_place" v-on:reloadLocation="reGetDatas"></SelectLocation>
  </div>
</template>

<script>
import Clothes from './Clothes/Clothes.vue'
import Weather from './Weather/Weather.vue'
import SelectLocation from './SelectLocation/SelectLocation.vue'
import axios from 'axios'

export default {
  name: 'app',
  data () {
    return {
      dataForTop: {}
    }
  },
  created: function () {
    this.getDatas()
    this.$emit('topEmit')
  },
  methods: {
    getDatas: function (event) {
      this.dataForTop = {
        weather: {
          weather_icon: '',
          max_temperature: 0,
          min_temperature: 0,
          humidity: 0
        },
        clothes: {
          clothes_description: '',
          man_clothes: {
            inner_image: '',
            inner_color: '',
            outer_image: '',
            outer_color: '',
            bottom_image: '',
            bottom_color: ''
          },
          woman_clothes: {
            inner_image: '',
            inner_color: '',
            outer_image: '',
            outer_color: '',
            bottom_image: '',
            bottom_color: ''
          }
        },
        user_place: ''
      }

      var self = this
      var url = '/api/v1/top'

      axios.get(url)
      .then(function (response) {
        self.dataForTop = response.data
      }).catch(function (error) {
        console.log(error)
      })
    },
    reGetDatas: function (location) {
      // location は emit してくるメソッドから送られてくる
      var self = this

      var baseUrl = '/api/v1/top?loc='
      var requestLocation = location + ',jp'

      var url = baseUrl + requestLocation

      axios.get(url)
      .then(function (response) {
        self.dataForTop = response.data
      }).catch(function (error) {
        console.log(error)
      })
    }
  },
  components: {
    Clothes,
    Weather,
    SelectLocation
  }
}
</script>

<style>
  .top {
    height: 100%;
    margin: 0;
    padding: 0;
  }
</style>