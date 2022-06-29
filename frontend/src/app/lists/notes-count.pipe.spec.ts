import { NotesCountPipe } from './notes-count.pipe';

describe('NotesCountPipe', () => {
  it('create an instance', () => {
    const pipe = new NotesCountPipe();
    expect(pipe).toBeTruthy();
  });
});
