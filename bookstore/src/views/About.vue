<template>
  <v-container class="grey lighten-5">
    <v-row>
      <v-col cols="4" lg="4" md="6" xs="12">
        <v-card outlined>
          <v-card-title>Create a New Book</v-card-title>
          <v-card-text>
            <v-text-field
              v-model="book.name"
              label="Name"
              outlined
            ></v-text-field>
            <v-text-field
              v-model="book.price"
              label="price"
              outlined
            ></v-text-field>
          </v-card-text>
          <v-card-actions>
            <v-btn outlined rounded text @click="createBook">
              Create Book
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
      <v-col cols="8">
        <v-row>
          <v-col cols="12" lg="4" md="6" xs="12">
            <v-card>
              <v-card-title>{{ book.name }}</v-card-title>
              <v-card-subtitle>{{ book.price }}</v-card-subtitle>
            </v-card>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
export default {
  name: "Add",
  data() {
    return {
      book: {
        name: "",
        price: "",
      },
    };
  },
  methods: {
    createBook() {
      axios.post("http://localhost:8080/api/books", this.book).then(() => {
        this.$router.push({ name: "Home" });
      });
    },
  },
};
</script>
