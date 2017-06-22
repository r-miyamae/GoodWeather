<template>
    <div class="top__cloth-header-parts">
        {{ gender }}
    </div>
</template>

<script>
export default {
    name: 'cloth-header-parts',
    props: ['gender']
}
</script>

<style>
.top__cloth-header-parts {
    width: 50%;
    padding-top: 1vh;
    border-bottom: solid 2px silver;
    border: solid 1px silver;
    font-weight: bold;
    font-size: 150%;
    text-align: center;
    color: #000050;
}
</style>