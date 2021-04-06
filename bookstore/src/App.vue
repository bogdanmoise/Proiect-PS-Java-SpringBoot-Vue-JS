<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <div class="d-flex align-center">
        <h1>Books</h1>
      </div>

      <v-spacer></v-spacer>

      <v-btn icon @click="$router.push({ name: 'Home' })">
        <v-icon>mdi-home</v-icon>
      </v-btn>
      <v-btn icon @click="$router.push({ name: 'Add' })">
        <v-icon>mdi-plus</v-icon>
      </v-btn>
    </v-app-bar>

    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script>
import HelloWorld from "./components/HelloWorld";

export default {
  name: "App",
  components: {
    HelloWorld,
  },
  data: () => ({
    
  }),
};
</script>
