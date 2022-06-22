export interface Note {
  description: string;
  isbn: string;
  listName: string;
}

export interface List {
  name: string;
  notes: Note[];
}
