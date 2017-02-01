<template>
  <div id="app">
    <Header></Header>
    <Clothes :clothes="dataForTop.clothes"></Clothes>
    <Weather :weather="dataForTop.weather"></Weather>
    <SelectLocation :location="dataForTop.user_place"></SelectLocation>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from './components/Header/Header.vue'
import Clothes from './components/Clothes/Clothes.vue'
import Weather from './components/Weather/Weather.vue'
import SelectLocation from './components/SelectLocation/SelectLocation.vue'
import Footer from './components/Footer/Footer.vue'

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
          weather_icon: 'fine.png',
          max_temperature: 30,
          min_temperature: 33.5,
          humidity: 70
        },
        clothes: {
          clothes_description: '今日は暑いので、薄手の服装がおすすめよぉおぉぉぉぉおおぉぉぉおぉおぉぉおおおおおぉぉぉぉぉぉおおぉ',
          man_clothes: {
            inner_image: '/assets/img/clothes/t-shut.png',
            inner_color: '#04f33d',
            outer_image: '/assets/img/clothes/cote.png',
            outer_color: '',
            bottom_image: '/assets/img/clothes/gens.png',
            bottom_color: '#9933ff'
          },
          woman_clothes: {
            inner_image: '/assets/img/clothes/t-shut.png',
            inner_color: '#ff3399',
            outer_image: '',
            outer_color: '',
            bottom_image: '/assets/img/clothes/gens.png',
            bottom_color: '#aa4455'
          }
        },
        user_place: '大阪府'
      }
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
