<template>
    <div class="hoge">
        <select class="top__selectLocation" v-model="location" options="options">
            <option v-for="option in options" :value="option.value">{{ option.text }}</option>
        </select>
    </div>
</template>

<script>
export default {
  updated: function () {
    this.$emit('reloadLocation', this.location) // v-on を使ってこのコンポーネントを参照している関数に this.location を渡す
  },
  data () {
    return {
      location: 'tokyo',
      options: [
        {value: 'hokkaido', text: '北海道'},
        {value: 'aomori', text: '青森県'},
        {value: 'iwate', text: '岩手県'},
        {value: 'miyagi', text: '宮城県'},
        {value: 'akita', text: '秋田県'},
        {value: 'yamagata', text: '山形県'},
        {value: 'fukushima', text: '福島県'},
        {value: 'ibaraki', text: '茨城県'},
        {value: 'tochigi', text: '栃木県'},
        {value: 'gunma', text: '群馬県'},
        {value: 'saitama', text: '埼玉県'},
        {value: 'chiba', text: '千葉県'},
        {value: 'tokyo', text: '東京都'},
        {value: 'kanagawa', text: '神奈川県'},
        {value: 'niigata', text: '新潟県'},
        {value: 'toyama', text: '富山県'},
        {value: 'ishikawa', text: '石川県'},
        {value: 'fukui', text: '福井県'},
        {value: 'yamanashi', text: '山梨県'},
        {value: 'nagano', text: '長野県'},
        {value: 'gifu', text: '岐阜県'},
        {value: 'shizuoka', text: '静岡県'},
        {value: 'aichi', text: '愛知県'},
        {value: 'mie', text: '三重県'},
        {value: 'shiga', text: '滋賀県'},
        {value: 'kyoto', text: '京都府'},
        {value: 'osaka', text: '大阪府'},
        {value: 'hyogo', text: '兵庫県'},
        {value: 'nara', text: '奈良県'},
        {value: 'wakayama', text: '和歌山県'},
        {value: 'tottori', text: '鳥取県'},
        {value: 'shimane', text: '島根県'},
        {value: 'okayama', text: '岡山県'},
        {value: 'hiroshima', text: '広島県'},
        {value: 'yamaguchi', text: '山口県'},
        {value: 'tokushima', text: '徳島県'},
        {value: 'kagawa', text: '香川県'},
        {value: 'ehime', text: '愛媛県'},
        {value: 'kochi', text: '高知県'},
        {value: 'fukuoka', text: '福岡県'},
        {value: 'saga', text: '佐賀県'},
        {value: 'nagasaki', text: '長崎県'},
        {value: 'kumamoto', text: '熊本県'},
        {value: 'oita', text: '大分県'},
        {value: 'miyazaki', text: '宮崎県'},
        {value: 'kagoshima', text: '鹿児島県'},
        {value: 'okinawa', text: '沖縄県'}
      ]
    }
  }
}
</script>

<style>
.top__selectLocation {
    -webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;

    width: 100%;    
    height: 8%;
    border: 0;
    background-color: #01288d;
    color: white;
    font-size: 5em;
}

.top__selectLocation option {
    font-size: 30%;
}

.hoge {
    width: 100%;
    display: -webkit-box;
    display: box;
    box-pack: center;
    -webkit-box-pack: center;
    -moz-box-pack: center;
    text-align-last: center;
    -webkit-box-align: center;
    background-color: #01288d;
}
</style>