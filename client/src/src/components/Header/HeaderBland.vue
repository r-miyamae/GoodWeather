<template>
    <div class="header__bland">
        <div class="header__bland-icon"></div>
        <h2 class="header__bland-name">
            <router-link to="/">Good Weather</router-link>
        </h2>
    </div>
</template>

<style>
.header__bland {
    padding: 0;
    width: 85%;
    display: flex;
}

.header__bland-name {
    margin: 0;
    font-size: 190%;
}

.header__bland-name a {
    text-decoration: none;
    color: #ffffff;
}

.header__bland-icon {
    /*background-image: url('~assets/good_weather_icon.png');*/
    background-size: contain;
    background-repeat: no-repeat;
    width: 3em;
    height: 3em;
}
</style>