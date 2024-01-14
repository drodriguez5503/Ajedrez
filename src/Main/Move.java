package Main;

import Piezas.Pieza;

public class Move {
    int colAnt;
    int filAnt;
    int newCol;
    int newFil;

    Pieza pieza;
    Pieza captura;

    public Move(Tablero tablero, Pieza pieza, int newCol, int newFil){
        this.colAnt =pieza.col;
        this.filAnt =pieza.fil;
        this.newCol = newCol;
        this.newFil = newFil;

        this.pieza = pieza;
        this.captura = tablero.getPieza(newCol,newFil);

    }
}
