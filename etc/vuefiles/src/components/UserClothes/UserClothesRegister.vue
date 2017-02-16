<template>
  <div class="register">
    <h3 class="register__caption">服装登録</h3>
    <form action="return false;" class="register__cloth-form">
      <label class="register__clothes-label" for="clothes-name">服の名前 <span class="register__clothes-label-optional">※任意</span></label>
      <input type="text" v-model="name" class="register__clothes-name" name="clothes-name" placeholder="例: 厚手のニットセーター">
      <label class="register__clothes-label" for="clothes-color">服の色</label>
      <compact-picker class="register__clothes-color" v-model="color" @change-color="onChange"></compact-picker>
      <label class="register__clothes-label" for="clothes-genre">ジャンル選択</label>
      <GenreSelect :color="color" v-on:submitGenre="submitGenre"></GenreSelect>
      <button class="register__clothes-submit" type="button" @click="post">登録</button>
  </div>
</template>

<script>
import GenreSelect from './GenreSelect.vue'
import { Compact } from 'vue-color'
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
  },
  data () {
    return {
      name: '',
      color: '#ffffff',
      genre_code: '',
      icon_name: ''
    }
  },
  components: {
    'compact-picker': Compact,
    GenreSelect
  },
  methods: {
    onChange: function (val) {
      this.color = val.hex
    },
    submitGenre: function (genre, iconName) {
      this.genre_code = genre
      this.icon_name = iconName
    },
    post: function () {
      var url = '/api/v1/user/clothes'

      console.log(this.name, this.color, this.genre_code, this.icon_name)

      axios.post(url, {
        name: this.name,
        color: this.color,
        genre: this.genre_code,
        icon: this.icon_name
      })
      .then(function (response) {
        if (response.status === 200) {
          window.location = '/#/'
        }
      })
      .catch(function (error) {
        console.log(error.response.status)
        if (error.response.status === 400) {
          // 登録失敗した時の処理
          // TODO: ここで何かしらの失敗したことを知らせるメッセージを画面に表示する
        }
      })
    }
  }
}
</script>

<style>
.register {
  height: 85%;
}

.register__caption {
  display: block;
  font-size: 4em;
  color: #333333;
  margin: 4%;
  margin-left: 5%;
  border-bottom: 4px solid #333333
}

.register__cloth-form {
  width: 90%;
  height: 80%;
  margin: 0 auto;
}

.register__cloth-form input {
  display: block;
  margin: 0 auto;
  width: 90%;
  height: 1em;
  font-size: 3em;
}

.register__clothes-label {
  display: block;
  font-size: 3em;
  color: #333333;
  margin: 2% auto;
  border-bottom: 4px solid #333333;
  width: 95%;
}

.register__clothes-label-optional {
  font-size: 0.5em;
  color: #666666;
}

/* color picker css */
.register__clothes-color {
  height: 200px !important;
  margin: 0 auto;
  width: 90% !important;
}

.vue-color__compact__color-item {
  height: 60px !important;
  width: 60px !important;
}

.register__clothes-submit {
  background-color: darkorange;
  color: #ffffff;
  border: 0;
  font-size: 4em;
  padding: 1% 5%;
  position: absolute;
  right: 5%;
}
</style>
