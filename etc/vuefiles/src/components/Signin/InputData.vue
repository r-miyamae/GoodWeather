<template>
  <div class="signin__inputData">
    <form action="">
      <label for="email">E-Mail</label><br>
      <input name="email" type="text"><br>
      <label for="password">Password</label><br>
      <input name="password" type="password"><br>
      <button class="signin__submit" type="submit">Sign In</button>
    </form>
  </div>
</template>

<style>
.signin__inputData {
  width: 80%;
  height: 90%;
  margin: 5% auto;
  font-size: 3em;
}

.signin__inputData input {
  font-size: 1em;
  border: 0;
  width: 100%;
  margin: 5% 0;
  border-bottom: 3px solid #01288d;
}

.signin__selectLocation {
  -webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;

  margin-top: 5%;
  padding: 3%;
  width: 100%;    
  height: 8%;
  border: 0;
  background-color: #01288d;
  color: white;
  font-size: 1.5em;
}

.signin__selectLocation option {
    font-size: 30%;
}

.signin__submit {
  font-size: 1.3em;
  border: 0;
  margin: 5% 0;
  padding: 1% 3%;
  position: absolute;
  right: 10%;
  background-color: darkorange;
  color: #ffffff;
}
</style>