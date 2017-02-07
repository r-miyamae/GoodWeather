<template>
  <div id="app">
    <Header></Header>
    <Clothes :clothes="dataForTop.clothes"></Clothes>
    <Weather :weather="dataForTop.weather"></Weather>
    <SelectLocation :location="dataForTop.user_place" v-on:reloadLocation="reGetDatas"></SelectLocation>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from './components/Header/Header.vue'
import Clothes from './components/Clothes/Clothes.vue'
import Weather from './components/Weather/Weather.vue'
import SelectLocation from './components/SelectLocation/SelectLocation.vue'
import Footer from './components/Footer/Footer.vue'
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
    Header,
    Clothes,
    Weather,
    SelectLocation,
    Footer
  }
}
</script>

<style>
#app {
  height: 100%;
  font-family: sans-serif;
}
</style>
