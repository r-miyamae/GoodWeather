<template>
  <div class="signin__inputData">
    <div class="signin__inputData-info">Please Sign in</div>
    <div v-if="isError" class="signin__inputData-error">Failed to Sign in</div>
    <form onsubmit="return false;">
      <label for="email">E-Mail</label><br>
      <input name="email" type="email" @keyup.enter="post" v-model="postData.email" required placeholder="Please Input Email Address"><br>
      <label for="password">Password</label><br>
      <input name="password" type="password" @keyup.enter="post" v-model="postData.password" required placeholder="Please Input Password"><br>
      <button class="signin__submit" type="button" @click="post">Sign In</button>
      <router-link class="signin__to-signup-link" to="/signup">Go to Sign up Page</router-link>
    </form>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  methods: {
    post: function () {
      if (this.validation(this.postData)) {
        var self = this
        var baseUrl = '/api/v1/user/signin'
        var mail = '?mailAddress=' + encodeURIComponent(this.postData.email)
        var password = '&password=' + this.postData.password

        var url = baseUrl + mail + password

        axios.post(url)
        .then(function (response) {
          console.log('done')
          if (response.status === 200) {
            window.location = '/#/'
          }
        })
        .catch(function (error) {
          console.log(error.response.status)
          if (error.response.status === 400) {
            // 登録失敗した時の処理
            self.postData.password = ''
            // TODO: ここで何かしらの失敗したことを知らせるメッセージを画面に表示する
            self.isError = true
          }
        })
      }
    },
    validation: function (data) {
      if (data.email !== '' && data.password !== '') {
        return true
      } else {
        return false
      }
    }
  },
  data () {
    return {
      isError: false,
      postData: {
        email: '',
        password: ''
      }
    }
  }
}
</script>

<style>
.signin__inputData {
  width: 80%;
  height: 90%;
  margin: 5% auto;
  font-size: 3em;
}

.signin__inputData input {
  font-size: 1em;
  border: 0;
  width: 100%;
  margin: 5% 0;
  border-bottom: 3px solid #01288d;
}

.signin__selectLocation {
  -webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;

  margin-top: 5%;
  padding: 3%;
  width: 100%;    
  height: 8%;
  border: 0;
  background-color: #01288d;
  color: white;
  font-size: 1.5em;
}

.signin__selectLocation option {
    font-size: 30%;
}

.signin__submit {
  font-size: 1.3em;
  border: 0;
  margin: 5% 0;
  padding: 1% 3%;
  position: absolute;
  right: 10%;
  background-color: darkorange;
  color: #ffffff;
}

.signin__inputData-info {
  background-color: #015fad;
  color: #ffffff;
  text-align: center;
  padding: 2%;
  margin-bottom: 5%;
}

.signin__inputData-error {
  background-color: indianred;
  width: 90%;
  text-align: center;
  padding: 5% 2%;
  margin: 2% auto; 
  border-radius: 10px;
  color: #ffffff;
}

.signin__to-signup-link {
  color: #01288d;
  display: block;
  margin-top: 22%;
}
</style>