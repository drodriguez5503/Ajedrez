package Piezas;

import Main.Tablero;

import java.awt.image.BufferedImage;

public class Dama extends Pieza{
    public Dama (Tablero tablero, int col, int fil, boolean esBlanca){
        super(tablero);
        this.col = col;
        this.fil = fil;
        this.posX = col* tablero.tamCasilla;
        this.posY = fil* tablero.tamCasilla;

        this.esBlanca = esBlanca;
        this.nombre = "Dama";

        this.sprite = sheet.getSubimage(sheetScale, esBlanca ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(sheetScale,sheetScale, BufferedImage.SCALE_SMOOTH);

    }

    @Override
    public boolean isValidMovement(int col, int fil) { //Ver si el movimiento es válido
        return Math.abs(this.col-col) == Math.abs(this.fil-fil) || this.col == col || this.fil == fil;
    }

    @Override
    public boolean moveColisionaConPieza(int col, int fil) { //Comprobar si hay otra pieza en el camino

        if(this.col == col || this.fil == fil) {
            //Izquierda
            if (this.col > col) {
                for (int c = this.col - 1; c > col; c--) {
                    if (tablero.getPieza(c, this.fil) != null) {
                        return true;
                    }
                }
            }

            //Derecha
            if (this.col < col) {
                for (int c = this.col + 1; c < col; c++) {
                    if (tablero.getPieza(c, this.fil) != null) {
                        return true;
                    }
                }
            }

            //Arriba
            if (this.fil > fil) {
                for (int f = this.fil - 1; f > fil; f--) {
                    if (tablero.getPieza(this.col, f) != null) {
                        return true;
                    }
                }
            }

            //Abajo
            if (this.fil < fil) {
                for (int f = this.fil + 1; f < fil; f++) {
                    if (tablero.getPieza(this.col, f) != null) {
                        return true;
                    }
                }
            }
        } else {
            // Arriba izquierda
            if(this.col > col && this.fil > fil){
                for(int i=1; i<Math.abs(this.col - col); i++){
                    if(tablero.getPieza(this.col - i,this.fil - i) != null){
                        return true;
                    }
                }

            }


            // Arriba derecha
            if(this.col < col && this.fil > fil){
                for(int i = 1 ; i< Math.abs(this.col-col); i++){
                    if(tablero.getPieza(this.col+i,this.fil-i)!=null){
                        return true;
                    }
                }
            }

            //Abajo izquierda
            if(this.col > col && this.fil < fil){
                for(int i=1; i<Math.abs(this.col-col); i++){
                    if(tablero.getPieza(this.col-i,this.fil+i)!=null){
                        return true;
                    }
                }
            }


            // Abajo derecha
            if(this.col < col && this.fil < fil){
                for(int i = 1 ; i< Math.abs(this.col-col); i++){
                    if(tablero.getPieza(this.col+i,this.fil+i)!=null){
                        return true;
                    }
                }
            }
        }

        return false;


    }


}