<template>
    <footer class="top__footer">
        <BacktoTop></BacktoTop>
        <div class="top__footer-contents">
            this is footer.
            <br>
            welcome to good weahter. <br>
            this application made by @ahah0807_alg &amp; @r-miyamae.
            <br>
            for media-frontier 2017.
            <br>
            &copy; Good Weather,. inc
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
    font-size: 3em;
}
</style>