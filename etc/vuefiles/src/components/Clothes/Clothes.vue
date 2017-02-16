<template>
    <div class="top__clothes">
      <div class="top__clothes-content" :class="{ 'top__clothes-back-side': isTurn }" @click="doTurn">
        <div class="top__clothes-front">
          <ClothHeader :gender="gender"></ClothHeader>
          <ClothImages :clothes="clothes" :gender="gender"></ClothImages>
        </div>
        <Icon :weatherImageName='weatherImageName' :temperature='temperature' :location='location'></Icon>
      </div>
      <OuterToggleButton v-on:hideOuter="hideOuter" v-on:indicateOuter="indicateOuter" :manOuterData="clothes.man_clothes.outer_image"></OuterToggleButton>        
    </div>
</template>

<script>
import ClothHeader from './ClothHeader.vue'
import ClothImages from './ClothImages.vue'
import Icon from '../Weather/Icon.vue'
import OuterToggleButton from './OuterToggleButton.vue'

export default {
  props: ['clothes', 'gender', 'weatherImageName', 'temperature', 'location'],
  components: {
    ClothHeader,
    ClothImages,
    Icon,
    OuterToggleButton
  },
  methods: {
    doTurn: function () {
      if (!this.isTurn) {
        this.isTurn = true
      } else {
        this.isTurn = false
      }
    },
    hideOuter: function () {
      // OuterToggleButton からhideOuterのemit（通知）が来た場合に呼ばれる
      if (this.isTurn) {
        // 裏側を向いていた場合、表に向ける
        this.isTurn = false
      }

      // アウターのファイル名を退避
      this.tmp_man_outer = this.clothes.man_clothes.outer_image
      this.tmp_woman_outer = this.clothes.woman_clothes.outer_image

      // アウターの画像ファイル名をクリアして非表示にする
      this.clothes.man_clothes.outer_image = ''
      this.clothes.woman_clothes.outer_image = ''
    },
    indicateOuter: function () {
      // OuterToggleButton からindicateOuterのemit（通知）が来た場合に呼ばれる
      if (this.isTurn && this.clothes.man_clothes.outer_image === '') {
        // 裏側を向いているかつアウターが非表示になっていた場合、表に向ける
        this.isTurn = false
      }

      // 退避させていたアウターのファイル名を復帰
      this.clothes.man_clothes.outer_image = this.tmp_man_outer
      this.clothes.woman_clothes.outer_image = this.tmp_woman_outer

      // アウターファイル名の退避場所をクリア
      this.tmp_man_outer = ''
      this.tmp_woman_outer = ''
    }
  },
  data () {
    return {
      isTurn: false,  // True だと裏返っている（説明文が表示されている）
      tmp_man_outer: '',
      tmp_woman_outer: ''
    }
  }
}
</script>

<style>
.top__clothes {
    height: 70%;
    position: relative;
    -webkit-transform-style: preserve-3d;
    transform-style: preserve-3d;
    -webkit-transition: -webkit-transform .6s;
    transition: .6s;
}
.top__clothes-content {
    height: 100%;
    position: relative;
    -webkit-transform-style: preserve-3d;
    transform-style: preserve-3d;
    -webkit-transition: -webkit-transform .6s;
    transition: .6s;
}

.top__clothes-back-side {
    -ms-transform:rotateY(180deg);
    -webkit-transform: rotateY(180deg);
    transform: rotateY(180deg);
}
.top__clothes-front {
    height: 100%;
}
</style>
