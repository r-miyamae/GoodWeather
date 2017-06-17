<template>
    <div class="header__bland">
        <div class="header__bland-icon"></div>
        <router-link to="/">
            <h2 class="header__bland-name">Good Weather</h2>
        </router-link>
    </div>
</template>

<style>
.header__bland {
    margin: 0;
    padding: 0;
    width: 85%;
    display: flex;
}
.header__bland a {
    text-decoration: none;
    color: #ffffff;
}
.header__bland-name {
    margin: 0.5em 0 0;
    font-size: 2em;
    width: 100%;
}
.header__bland-icon {
    /*background-image: url('~assets/good_weather_icon.png');*/
    background-size: contain;
    background-repeat: no-repeat;
    margin-left: 3%;
    width: 3em;
    height: 3em;
}
</style>