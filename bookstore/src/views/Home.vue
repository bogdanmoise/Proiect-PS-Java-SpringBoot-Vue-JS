<template>
  <v-container class="grey lighten-5">
    <v-row>
      <v-col cols="8">
        <v-row>
          <v-col
            cols="12"
            lg="4"
            md="6"
            xs="12"
            v-for="book in books"
            :key="book.id"
          >
            <v-card @click="selectBook(book)">
              <v-img src="account_books.jpg" height="200px"></v-img>
              <v-card-title> {{ book.name }} </v-card-title>
              <v-card-subtitle>
                {{ book.price }}
              </v-card-subtitle>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn icon @click.stop="deleteBook(book)">
                  <v-icon>mdi-delete</v-icon>
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-col>
      <v-col cols="4" lg="4" md="6" xs="12">
        <v-card outlined v-if="selectedBook !== null">
          <v-card-title>Update Book</v-card-title>
          <v-card-text>
            <v-text-field
              v-model="selectedBook.name"
              label="Name"
              outlined
            ></v-text-field>
            <v-text-field
              inline
              controls
              v-model="selectedBook.price"
              label="Price"
              outlined
            ></v-text-field>
          </v-card-text>
          <v-card-actions>
            <v-btn outlined rounded text @click="updateBook">
              Save Changes
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
import lodash from "lodash";
export default {
  name: "Home",
  data() {
    return {
      books: [],
      selectedBook: null,
    };
  },
  created() {
    this.loadBooks();
  },
  methods: {
    loadBooks() {
      axios
        .get("http://localhost:8080/api/books")
        .then((response) => {
          this.books = response.data;
        })
        .catch((err) => {});
    },
    selectBook(book) {
      this.selectedBook = lodash.cloneDeep(book);
    },
    updateBook() {
      axios
        .put(
          `http://localhost:8080/api/books/${this.selectedBook.id}`,
          this.selectedBook
        )
        .then((response) => {
          this.loadBooks();
        });
    },
    deleteBook(book) {
      axios
        .delete(`http://localhost:8080/api/books/${book.id}`)
        .then((response) => {
          this.loadbooks();
          this.selectedBook = null;
        });
    },
  },
};
</script>
