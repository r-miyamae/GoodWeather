<template>
    <footer class="top__footer">
        <BacktoTop></BacktoTop>
        <div class="top__footer-contents">
            GoodWeather をご覧頂きありがとうございます。<br>
            OIC（専門学校）の作品展 Media Frontier 2017 のための作品です。<br>
            制作: @ahaha0807_alg (Twitter) &amp; @r-miyamae &amp; 同学校デザイナー の3名で作成しました。<br>
            日本の制度が高い天気情報API や 服系通販サイトのご協力をお待ちしています。
        </div>
    </footer>
</template>

<script>
import BacktoTop from './BacktoTop.vue'

export default {
  components: {
    BacktoTop
  }
}
</script>

<style>
.top__footer {
    width: 100%;
    height: 30%;
    background-color: #cccccc;
}

.top__footer-contents {
    font-size: 2.6em;
    padding: 3%;
}
</style>