package Main;

import Piezas.Pieza;

public class EscanerJaques {
    Tablero tablero;

    public EscanerJaques(Tablero tablero) {
        this.tablero = tablero;

    }

    public boolean hayJaque(Move move){
        Pieza rey = tablero.encuentraRey(move.pieza.esBlanca);
        assert rey != null;

        int colRey = rey.col;
        int filRey = rey.fil;

        if(tablero.piezaSeleccionada != null && tablero.piezaSeleccionada.nombre.equals("Rey")){
            colRey = move.newCol;
            filRey = move.newFil;
        }

        return  hitByTorre(move.newCol, move.newFil, rey, colRey, filRey, 0,1) ||  // arriba
                hitByTorre(move.newCol, move.newFil, rey, colRey, filRey, 1,0) ||  // der
                hitByTorre(move.newCol, move.newFil, rey, colRey, filRey, 0,-1) || // abajo
                hitByTorre(move.newCol, move.newFil, rey, colRey, filRey, -1,0) || // izq

                hitByAlfil(move.newCol, move.newFil, rey, colRey, filRey, -1,-1) ||  // arriba izq
                hitByAlfil(move.newCol, move.newFil, rey, colRey, filRey, 1,-1) ||   // arriba der
                hitByAlfil(move.newCol, move.newFil, rey, colRey, filRey, 1,1) ||   // abajo der
                hitByAlfil(move.newCol, move.newFil, rey, colRey, filRey, -1,1) ||  // abajo izq

                hitByCaballo(move.newCol,move.newFil,rey,colRey,filRey) ||
                hitByPeon(move.newCol,move.newFil,rey,colRey,filRey) ||
                hitByRey(rey,colRey,filRey);

    }

    private boolean hitByTorre(int col, int fil,Pieza rey, int colRey, int filRey, int colVal, int filVal){
        for (int i = 1; i < 8; i++) {
            if(colRey + (i*colVal) == col && filRey + (i*filVal) == fil){
                break;
            }

            Pieza pieza = tablero.getPieza(colRey+(i*colVal), filRey + (i*filVal)) ;

            if(pieza != null && pieza != tablero.piezaSeleccionada) {
                if(!tablero.mismoBando(pieza, rey) && ( pieza.nombre.equals("Torre") || pieza.nombre.equals("Dama"))) {
                    return true;
                }

                break;
            }

        }
       return false;
    }

    private boolean hitByAlfil(int col, int fil,Pieza rey, int colRey, int filRey, int colVal, int filVal){
        for (int i = 1; i < 8; i++) {
            if(colRey - (i*colVal) == col && filRey - (i*filVal) == fil){
                break;
            }

            Pieza pieza = tablero.getPieza(colRey - (i*colVal), filRey - (i*filVal)) ;

            if(pieza != null && pieza != tablero.piezaSeleccionada) {
                if(!tablero.mismoBando(pieza, rey) && ( pieza.nombre.equals("Alfil") || pieza.nombre.equals("Dama"))) {
                    return true;
                }

                break;
            }

        }
        return false;
    }


    private boolean hitByCaballo (int col, int fil, Pieza rey, int colRey, int filRey){
        return  jaqueCaballo(tablero.getPieza(colRey-1,filRey-2), rey, col, fil) ||
                jaqueCaballo(tablero.getPieza(colRey+1,filRey-2), rey, col, fil) ||
                jaqueCaballo(tablero.getPieza(colRey+2,filRey-1), rey, col, fil) ||
                jaqueCaballo(tablero.getPieza(colRey+2,filRey+1), rey, col, fil) ||
                jaqueCaballo(tablero.getPieza(colRey+1,filRey+2), rey, col, fil) ||
                jaqueCaballo(tablero.getPieza(colRey-1,filRey+2), rey, col, fil) ||
                jaqueCaballo(tablero.getPieza(colRey-2,filRey+1), rey, col, fil) ||
                jaqueCaballo(tablero.getPieza(colRey-2,filRey-1), rey, col, fil);

    }

    private boolean jaqueCaballo (Pieza p, Pieza k, int col, int fil){
        return p != null && !tablero.mismoBando(p,k) && p.nombre.equals("Caballo") && !(p.col == col && p.fil == fil);
    }

    private boolean hitByRey (Pieza rey, int colRey, int filRey) {
        return  jaqueRey(tablero.getPieza(colRey-1,filRey-1),rey) ||
                jaqueRey(tablero.getPieza(colRey+1,filRey-1),rey) ||
                jaqueRey(tablero.getPieza(colRey,filRey-1),rey) ||
                jaqueRey(tablero.getPieza(colRey-1,filRey),rey) ||
                jaqueRey(tablero.getPieza(colRey+1,filRey),rey) ||
                jaqueRey(tablero.getPieza(colRey-1,filRey+1),rey) ||
                jaqueRey(tablero.getPieza(colRey+1,filRey+1),rey) ||
                jaqueRey(tablero.getPieza(colRey,filRey+1),rey);

    }

    private boolean jaqueRey (Pieza p, Pieza r){
        return p != null && !tablero.mismoBando(p,r) && p.nombre.equals("Rey");

    }

    private boolean hitByPeon (int col, int fil, Pieza rey, int colRey, int filRey){
        int colVal = rey.esBlanca ? -1 : 1;

        return  jaquePeon(tablero.getPieza(colRey+1, filRey+colVal), rey, col, fil) ||
                jaquePeon(tablero.getPieza(colRey-1, filRey+colVal), rey, col, fil);

    }

    private boolean jaquePeon (Pieza p, Pieza r, int col, int fil) {
        return p != null && !tablero.mismoBando(p,r) && p.nombre.equals("Peon") && !(p.col == col && p.fil == fil);
    }

}
