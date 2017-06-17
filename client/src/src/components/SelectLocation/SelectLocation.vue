<template>
    <div class="top__selectLocation-wrapper">
        <select class="top__selectLocation" v-model="location" options="options">
            <option v-for="option in options" :value="option.value">{{ option.text }}</option>
        </select>
    </div>
</template>

<script>
export default {
  props: ['reciveLocation'],
  updated: function () {
    this.$emit('reloadLocation', this.location) // v-on を使ってこのコンポーネントを参照している関数に this.location を渡す
  },
  data () {
    return {
      location: 'nonlocation',
      options: [
        {value: 'nonlocation', text: '別の都道府県'},
        {value: 'sapporo', text: '北海道'},
        {value: 'aomori', text: '青森県'},
        {value: 'morioka', text: '岩手県'},
        {value: 'Sendai', text: '宮城県'},
        {value: 'akita', text: '秋田県'},
        {value: 'yamagata', text: '山形県'},
        {value: 'fukushima', text: '福島県'},
        {value: 'mito', text: '茨城県'},
        {value: 'utsunomiya', text: '栃木県'},
        {value: 'maebashi', text: '群馬県'},
        {value: 'saitama', text: '埼玉県'},
        {value: 'chiba', text: '千葉県'},
        {value: 'tokyo', text: '東京都'},
        {value: 'yokohama', text: '神奈川県'},
        {value: 'niigata', text: '新潟県'},
        {value: 'toyama', text: '富山県'},
        {value: 'kanazawa', text: '石川県'},
        {value: 'fukui', text: '福井県'},
        {value: 'yamanashi', text: '山梨県'},
        {value: 'nagano', text: '長野県'},
        {value: 'gifu', text: '岐阜県'},
        {value: 'shizuoka', text: '静岡県'},
        {value: 'nagoya', text: '愛知県'},
        {value: 'tsu', text: '三重県'},
        {value: 'otsu', text: '滋賀県'},
        {value: 'kyoto', text: '京都府'},
        {value: 'osaka', text: '大阪府'},
        {value: 'Kobe', text: '兵庫県'},
        {value: 'nara', text: '奈良県'},
        {value: 'wakayama', text: '和歌山県'},
        {value: 'tottori', text: '鳥取県'},
        {value: 'matsue', text: '島根県'},
        {value: 'okayama', text: '岡山県'},
        {value: 'hiroshima', text: '広島県'},
        {value: 'yamaguchi', text: '山口県'},
        {value: 'tokushima', text: '徳島県'},
        {value: 'takamatsu', text: '香川県'},
        {value: 'matsuyama', text: '愛媛県'},
        {value: 'kochi', text: '高知県'},
        {value: 'fukuoka', text: '福岡県'},
        {value: 'saga', text: '佐賀県'},
        {value: 'nagasaki', text: '長崎県'},
        {value: 'kumamoto', text: '熊本県'},
        {value: 'oita', text: '大分県'},
        {value: 'miyazaki', text: '宮崎県'},
        {value: 'kagoshima', text: '鹿児島県'},
        {value: 'naha', text: '沖縄県'}
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
  height: 100%;
  border: 0;
  background-color: #01288d;
  color: white;
  font-size: 5em;
}

.top__selectLocation option {
    font-size: 30%;
}

.top__selectLocation-wrapper {
    width: 100%;
    height: 9%;
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
