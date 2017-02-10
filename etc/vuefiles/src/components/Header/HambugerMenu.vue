<template>
	<div class="header__menu-button">
		<a class="menu-trigger" :class="{ active: isActive }" @click="menuToggle"> <!--アニメーションさせるためにこいつに active classを付与する-->
			<span></span>
			<span></span>
			<span></span>
		</a>
	</div>
</template>

<script>
export default {
  data () {
    return {
      isActive: false
    }
  },
  methods: {
    menuToggle: function () {
      if (!this.isActive) {
        this.isActive = true
        this.$emit('changeToggle', true)
      } else {
        this.isActive = false
        this.$emit('changeToggle', false)
      }
    }
  }
}
</script>

<style>
.header__menu-button {
	margin: 0;
	padding: 0;
	width: 10%;
	height: 100%;
	z-index: 1000;
}

.menu-trigger,
.menu-trigger span {
	display: inline-block;
	transition: all .4s;
	box-sizing: border-box;
}
.menu-trigger {
	margin-top: 1em;
	position: relative;
	width: 100%;
	height: 2em;
	font-size: 0.7em;
}
.menu-trigger span {
	position: absolute;
	left: 0;
	width: 100%;
	height: 20%;
	background-color: #ffffff;
	border-radius: 4px;
}
.menu-trigger span:nth-of-type(1) {
	top: 0;
}
.menu-trigger span:nth-of-type(2) {
	top: 0.8em;
}
.menu-trigger span:nth-of-type(3) {
	bottom: 0;
}

.menu-trigger.active span:nth-of-type(1) {
	-webkit-transform: translateY(20px) rotate(-45deg);
	transform: translateY(20px) rotate(-45deg);
}
.menu-trigger.active span:nth-of-type(2) {
	opacity: 0;
}
.menu-trigger.active span:nth-of-type(3) {
	-webkit-transform: translateY(-20px) rotate(45deg);
	transform: translateY(-20px) rotate(45deg);
}
</style>