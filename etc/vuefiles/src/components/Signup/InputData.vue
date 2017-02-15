<template>
  <div class="signup__inputData">
    <div v-if="isError" class="signup__inputData-error">Failed to Sign up</div>
    <form onsubmit="return false;">
      <label for="email">E-Mail</label><br>
      <input name="email" type="email" @keyup.enter="post" v-model="postData.email" required placeholder="Please Input Email Address"><br>
      <label for="password">Password</label><br>
      <input name="password" type="password" @keyup.enter="post" v-model="postData.password" required placeholder="Please Input Password"><br>
      <label for="location">Your Location</label><br><br>
      よく確認する地点を登録することで、TOPページに表示される天気の基準点が変更されます
      <select class="signup__selectLocation" name="location" v-model="postData.location" options="options">
        <option v-for="option in options" :value="option.value">{{ option.text }}</option>
      </select>
      <div class="signup__selectSex">
        <br>
        <label for="sex">Gender</label><br><br>
        MEN <input type="radio" value="MEN" name="sex" required v-model="postData.sex">
        WOMEN <input type="radio" value="WOMEN" name="sex" required v-model="postData.sex">
      </div>
      <button class="signup__submit" @click="post">SignUp</button>
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
        var baseUrl = '/api/v1/user/signup'
        var mail = '?mailAddress=' + encodeURIComponent(this.postData.email)
        var password = '&password=' + this.postData.password
        var location = '&loc=' + this.postData.location + ',jp'
        var gender = '&gender=' + this.postData.sex

        var url = baseUrl + mail + password + location + gender

        axios.post(url)
        .then(function (response) {
          if (response.status === 200) {
            window.location = '/#/signin'
          }
        })
        .catch(function (error) {
          if (error.response.status === 400) {
            // 登録失敗した時の処理
            self.postData.email = ''
            self.postData.password = ''
            self.postData.location = 'tokyo'
            self.postData.sex = ''
            self.isError = true
          }
        })
      }
    },
    validation: function (data) {
      if (data.email !== '' && data.password !== '' && data.sex !== '') {
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
        password: '',
        sex: '',
        location: 'tokyo'
      },
      options: [
        {value: 'Sapporo', text: '北海道'},
        {value: 'aomori', text: '青森県'},
        {value: 'Morioka', text: '岩手県'},
        {value: 'Sendai', text: '宮城県'},
        {value: 'akita', text: '秋田県'},
        {value: 'yamagata', text: '山形県'},
        {value: 'fukushima', text: '福島県'},
        {value: 'Mito', text: '茨城県'},
        {value: 'Utsunomiya', text: '栃木県'},
        {value: 'Maebashi', text: '群馬県'},
        {value: 'saitama', text: '埼玉県'},
        {value: 'chiba', text: '千葉県'},
        {value: 'tokyo', text: '東京都'},
        {value: 'Yokohama', text: '神奈川県'},
        {value: 'niigata', text: '新潟県'},
        {value: 'toyama', text: '富山県'},
        {value: 'Kanazawa', text: '石川県'},
        {value: 'fukui', text: '福井県'},
        {value: 'yamanashi', text: '山梨県'},
        {value: 'nagano', text: '長野県'},
        {value: 'gifu', text: '岐阜県'},
        {value: 'shizuoka', text: '静岡県'},
        {value: 'Nagoya', text: '愛知県'},
        {value: 'Tsu', text: '三重県'},
        {value: 'Otsu', text: '滋賀県'},
        {value: 'kyoto', text: '京都府'},
        {value: 'osaka', text: '大阪府'},
        {value: 'Kobe', text: '兵庫県'},
        {value: 'nara', text: '奈良県'},
        {value: 'wakayama', text: '和歌山県'},
        {value: 'tottori', text: '鳥取県'},
        {value: 'Matsue', text: '島根県'},
        {value: 'okayama', text: '岡山県'},
        {value: 'hiroshima', text: '広島県'},
        {value: 'yamaguchi', text: '山口県'},
        {value: 'tokushima', text: '徳島県'},
        {value: 'Takamatsu', text: '香川県'},
        {value: 'Matsuyama', text: '愛媛県'},
        {value: 'kochi', text: '高知県'},
        {value: 'fukuoka', text: '福岡県'},
        {value: 'saga', text: '佐賀県'},
        {value: 'nagasaki', text: '長崎県'},
        {value: 'kumamoto', text: '熊本県'},
        {value: 'oita', text: '大分県'},
        {value: 'miyazaki', text: '宮崎県'},
        {value: 'kagoshima', text: '鹿児島県'},
        {value: 'Naha', text: '沖縄県'}
      ]
    }
  }
}
</script>

<style>
.signup__inputData {
  width: 80%;
  height: 90%;
  margin: 5% auto;
  font-size: 3em;
}

.signup__inputData input {
  font-size: 1em;
  border: 0;
  width: 100%;
  margin: 5% 0;
  border-bottom: 3px solid #01288d;
}

.signup__selectLocation {
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

.signup__selectLocation option {
    font-size: 30%;
}

.signup__submit {
  font-size: 1.3em;
  border: 0;
  margin: 5% 0;
  padding: 1% 3%;
  position: absolute;
  right: 10%;
  background-color: darkorange;
  color: #ffffff;
}

.signup__selectSex {
  border-bottom: 4px solid #01288d;
}

.signup__selectSex input {
  width: 1em;
  height: 1em;
  margin: 2% 5%;
}

.signup__inputData-error {
  background-color: indianred;
  width: 90%;
  text-align: center;
  padding: 5% 2%;
  margin: 2% auto; 
  border-radius: 10px;
  color: #ffffff;
}
</style>
