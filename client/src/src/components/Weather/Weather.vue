<template>
    <div class="top__clothes-description">
        <ClothesDescription :dayDescription="dayDescription" :nightDescription="nightDescription"></ClothesDescription>
    </div>
</template>

<script>
import ClothesDescription from '../Clothes/ClothesDescription.vue'

export default {
    props: ['dayDescription', 'nightDescription'],
    components: {
        ClothesDescription
    }
}
</script>

<style>
.top__clothes-description {
    width: 100%;
    height: 20%;
    overflow: hidden;
}
</style>