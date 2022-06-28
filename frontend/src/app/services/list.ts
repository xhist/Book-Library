export interface Note {
  description: string;
  isbn: string;
  listName: string;
}

export interface Notes {
  noteDtos: Note[];
}

export interface List {
  name: string;
}

export interface ListNote {
  name: string;
  isbn: string;
  title: string;
  note: string;
}
