<template>
    <footer class="top__footer">
        <BacktoTop></BacktoTop>
        <div class="top__footer-contents">
            <router-link to="/">TOP</router-link>
            <router-link to="/howtouse">使い方</router-link>
            <router-link to="/madeby">製作者</router-link>
            <!-- &copy;Good Weather. @ahaha0807_alg.-->
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
    height: 15%;
    background-color: #aaaaaa;
}

.top__footer-contents {
    margin: 0 auto;
    text-align: center;
    width: 80%;
    overflow: hidden;
    padding-top: 1%;
}

.top__footer-contents a {
    padding: 0 1%;
    font-size: 120%;
    text-decoration: none;
    color: #ffffff;
}

.top__footer-contents a:hover {
    color: #01288d;
}
</style>