<template>
  <div class="register__clothes-genre-select" :style="{backgroundColor: color}">
    <ul>
      <div v-for="genre in genres" @click="selectGenre(genre.value, genre.cloth_id, genre.icon)">
        <li class="register__clothes-genre-select-data">
          {{genre.text}} <span class="register__clothes-genre-select-data-triangle">▼</span>
        </li>
        <img :class="{icon : isIcon}"class="register__clothes-genre-select-icon" v-if="genre.icon !== undefined && genre.cloth_id == selected_cloth" :src="'/assets/clothes/'+ genre.icon" :alt="genre.text">
      </div>
    </ul>
  </div>
</template>

<script>
function selectType (gender, code, self) {
  for (var i = 0; i < self.clothes.length; i++) { // 線形探索
    if (self.clothes[i].type === code) {
      if (gender === '1' && code === '2') { // MEN BUTTOM
        return self.clothes[2].cloth_list
      } else if (gender === '2' && code === '2') { // WOMEN BUTTOM
        return self.clothes[3].cloth_list
      } else if (code === '3') { // ONE-PIECE
        return self.clothes[4].cloth_list
      } else {
        return self.clothes[code].cloth_list
      }
    }
  }
}

export default {
  props: ['color'],
  mounted: function () {
    this.genres = this.gender
  },
  methods: {
    selectGenre: function (selectedCode, clothId, iconName) {
      if (this.genre_code === '') { // 男女
        this.genres = this.cloth_type
        this.genre_code += selectedCode
      } else if (this.genre_code === '1' || this.genre_code === '2') { // 服のタイプ
        this.genres = selectType(this.genre_code, selectedCode, this)
        this.genre_code += selectedCode
      } else { // 服の選択
        this.selected_cloth = clothId
        this.genre_code += selectedCode

        // アイコンが選ばれるたびにそのアイコンのジャンルIDに変更する
        if (this.genre_code.length >= 3) {
          this.genre_code = this.genre_code.slice(0, 2) + selectedCode
        }
        // 親にジャンルコードを伝える
        this.$emit('submitGenre', this.genre_code, iconName)

        // 服のアイコンの表示・非表示処理
        if (!this.isIcon) {
          this.isIcon = true
        } else {
          this.isIcon = false
        }
      }
    }
  },
  data () {
    return {
      isIcon: false,
      selected_cloth: '',
      genre_code: '',
      genres: {},
      gender: [
        {text: 'MEN', value: '1'},
        {text: 'WOMEN', value: '2'}
      ],
      cloth_type: [
        {text: 'INNER', value: '0'},
        {text: 'OUTER', value: '1'},
        {text: 'BUTTOM', value: '2'},
        {text: 'ONE PIECE', value: '3'}
      ],
      clothes: [
        {
          type: '0',
          cloth_list: [ // INNER
            {text: 'T-SHIRT(SHORT-THIN)', value: '1', cloth_id: '1', icon: 't-shirt-short.png'},
            {text: 'T-SHIRT(SHORT-THICK)', value: '2', cloth_id: '2', icon: 't-shirt-short.png'},
            {text: 'T-SHIRT(LONG-THIN)', value: '1', cloth_id: '3', icon: 't-shirt-long.png'},
            {text: 'T-SHIRT(LONG-THICK)', value: '2', cloth_id: '4', icon: 't-shirt-long.png'},
            {text: 'DRESS-SHIRT(SHORT)', value: '1', cloth_id: '5', icon: 'dress-shirt-short.png'},
            {text: 'DRESS-SHIRT(LONG)', value: '1', cloth_id: '6', icon: 'dress-shirt-long.png'},
            {text: 'POLO-SHIRT', value: '1', cloth_id: '7', icon: 'polo-shirt.png'}
          ]
        },
        {
          type: '1',
          cloth_list: [ // OUTER
            {text: 'CARDIGAN', value: '1', cloth_id: '8', icon: 'cardigan.png'},
            {text: 'JEAN-JACKET', value: '1', cloth_id: '9', icon: 'jean-jacket.png'},
            {text: 'PARKER(THIN)', value: '1', cloth_id: '10', icon: 'parker.png'},
            {text: 'PARKER(THICK)', value: '2', cloth_id: '11', icon: 'parker.png'},
            {text: 'KNITTED-SWEATER', value: '2', cloth_id: '12', icon: 'knitted-sweater.png'},
            {text: 'DOWN-JACKET', value: '2', cloth_id: '13', icon: 'down-jacket.png'},
            {text: 'CHESTER-COAT', value: '2', cloth_id: '14', icon: 'chester-coat.png'},
            {text: 'DUFFLE-COAT', value: '2', cloth_id: '15', icon: 'duffle-coat.png'},
            {text: 'PEA-COAT', value: '2', cloth_id: '16', icon: 'pea-coat.png'}
          ]
        },
        {
          type: '2',
          cloth_list: [ // MEN BUTTOM
            {text: 'HALF-PANTS', value: '1', cloth_id: '17', icon: 'half-pants.png'},
            {text: 'JEANS', value: '2', cloth_id: '18', icon: 'jeans.png'},
            {text: 'SLACKS', value: '2', cloth_id: '19', icon: 'slacks.png'},
            {text: 'CHINO-PANTS', value: '2', cloth_id: '20', icon: 'chino-pants.png'}
          ]
        },
        {
          type: '2',
          cloth_list: [ // WOMEN BUTTOM
            {text: 'JEANS', value: '2', cloth_id: '21', icon: 'jeans.png'},
            {text: 'SLACKS', value: '2', cloth_id: '22', icon: 'slacks.png'},
            {text: 'GAUCHO-PANTS', value: '2', cloth_id: '23', icon: 'gaucho-pants.png'},
            {text: 'SHORT-PANTS', value: '1', cloth_id: '24', icon: 'short-pants.png'},
            {text: 'LONG-SKIRT', value: '4', cloth_id: '25', icon: 'long-skirt.png'},
            {text: 'MINI-SKIRT', value: '3', cloth_id: '26', icon: 'mini-skirt.png'},
            {text: 'SHORT-PANTS-ON-TIGHTS', value: '2', cloth_id: '27', icon: 'short-pants-on-tights.png'},
            {text: 'SKIRT-ON-TIGHTS', value: '2', cloth_id: '28', icon: 'skirt-on-tights.png'}
          ]
        },
        {
          type: '3',
          cloth_list: [ // ONE-PIECE
            {text: 'ONE-PIECE', value: '1', cloth_id: '29', icon: 'onepiece.png'},
            {text: 'LONG-ONE-PIECE', value: '2', cloth_id: '30', icon: 'long-onepiece.png'}
          ]
        }
      ]
    }
  }
}
</script>

<style>
.register__clothes-genre-select {
  height: 60%;
  width: 90%;
  margin: 0 auto;
  background-color: #dddddd;
  overflow: scroll;
}

.register__clothes-genre-select ul {
  margin: 0;
  padding: 0;
}

.register__clothes-genre-select-data {
  list-style: none;
  font-size: 3em;
  background-color: #888888;
  color: #ffffff;
  width: 96%;
  height: 1em;
  margin: 0;
  padding: 5% 2%;
  border-bottom: 4px solid #444444;
}

.register__clothes-genre-select-data-triangle {
  -moz-transform: rotate(-90deg);
  -webkit-transform: rotate(-90deg);
  -o-transform: rotate(-90deg);
  -ms-transform: rotate(-90deg);
  transform: rotate(-90deg);
  
  font-size: 0.9em;
  display: inline-block;
}

.register__clothes-genre-select-icon {
  display: none;
}

.icon {
  width: 70%;
  margin: 0 auto;
  display: block;
}
</style>