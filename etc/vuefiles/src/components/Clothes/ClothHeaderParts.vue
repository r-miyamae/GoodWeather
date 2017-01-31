<template>
    <div :class="'top__cloth-header-' + gender " class="top__cloth-header-parts">
        <h2>{{ gender }}</h2>
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
    margin: 0;
    padding: 0;
    width: 50%;
    border-bottom: solid 4px silver;
}

.top__cloth-header-parts h2 {
    margin: 0;
    padding: 3% 0;
    font-size: 4em;
    text-align: center;
    color: #000050;
}

.top__cloth-header-MENS {
    border-right: solid 4px silver;
}

</style>