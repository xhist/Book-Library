export interface Author {
  firstname: string;
  lastname: string;
}

export interface Genre {
  name: string;
}

export interface Book {
  isbn: string;
  title: string;
  author: Author;
  genres: Genre[];
}
