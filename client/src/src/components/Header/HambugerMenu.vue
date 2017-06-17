<template>
	<div class="header__menu-button" @click="menuToggle">
		<a class="menu-trigger" :class="{ active: menuActive }">
			<!--アニメーションさせるためにこいつに active classを付与する-->
			<span></span>
			<span></span>
			<span></span>
		</a>
	</div>
</template>

<script>
export default {
	props: ['menuActive'],
	methods: {
		menuToggle: function () {
			if (this.menuActive) {
				this.$emit('changeMenu', true)
			} else {
				this.$emit('changeMenu', false)
			}
		}
	}
}
</script>

<style>
.header__menu-button {
	position: absolute;
	right: 1em;
	width: 35px;
	margin-top: 5px;
	padding: 0;
	z-index: 1000;
	cursor: pointer;
}

.menu-trigger,
.menu-trigger span {
	display: inline-block;
	transition: all .4s;
	box-sizing: border-box;
	cursor: pointer;
}

.menu-trigger {
	position: relative;
	width: 100%;
	height: 1.5em;
}

.menu-trigger span {
	position: absolute;
	width: 35px;
	height: 3px;
	background-color: white;
	border-radius: 4px;
}

.menu-trigger span:nth-of-type(1) {
	top: 0;
}

.menu-trigger span:nth-of-type(2) {
	top: 10px;
}

.menu-trigger span:nth-of-type(3) {
	bottom: 0;
}

.menu-trigger.active span:nth-of-type(1) {
	-webkit-transform: translateY(11px) rotate(-45deg);
	transform: translateY(11px) rotate(-45deg);
}

.menu-trigger.active span:nth-of-type(2) {
	opacity: 0;
}

.menu-trigger.active span:nth-of-type(3) {
	-webkit-transform: translateY(-11px) rotate(45deg);
	transform: translateY(-11px) rotate(45deg);
}
</style>