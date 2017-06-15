import { Square } from './square'

export class Gameboard {
  playerName: string;
  gameName: string;
  gameBoardName: string;
  board: Square[];
  bingo: boolean;
  gameover: boolean;
}
