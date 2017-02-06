<template>
    <div class="top__clothes-description">
        <p class="top__clothes-description-content">
            昼 : {{ dayDescription }} <br>
            夜 : {{ nightDescription }}
        </p>
        <span class="top__clothes-description-info">／</span>
    </div>
</template>

<script>
export default {
  props: ['dayDescription', 'nightDescription']
}
</script>

<style>
.top__clothes-description {
    width: 100%;
    height: 90%;
    position: absolute;
    top: 10%;
    

    -webkit-backface-visibility: hidden;
    backface-visibility: hidden;

    background-color: lightblue;
    -ms-transform:rotateY(180deg);
    -webkit-transform: rotateY(180deg);
    transform: rotateY(180deg);
}

.top__clothes-description-content {
    width: 80%;
    margin: 0 auto;
    margin-top: 8vh;
    position: relative;
    text-align: center;
    color: darkblue;
    font-weight: bold;
    font-size: 3.5em;
}

.top__clothes-description-info {
    position: absolute;
    right: 0;
    bottom: 0%;
    color: white;
    font-weight: bold;
    font-size: 3em;
}
</style>