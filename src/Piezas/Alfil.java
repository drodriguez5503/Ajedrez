package Piezas;

import Main.Tablero;

import java.awt.image.BufferedImage;

public class Alfil extends Pieza{
    public Alfil (Tablero tablero, int col, int fil, boolean esBlanca){
        super(tablero);
        this.col = col;
        this.fil = fil;
        this.posX = col * tablero.tamCasilla;
        this.posY = fil * tablero.tamCasilla;

        this.esBlanca = esBlanca;
        this.nombre = "Peón";

        this.sprite = sheet.getSubimage(2 * sheetScale, esBlanca ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(sheetScale,sheetScale, BufferedImage.SCALE_SMOOTH);

    }

    @Override
    public boolean isValidMovement(int col, int fil) { //Ver si el movimiento es válido
        return Math.abs(this.col-col) == Math.abs(this.fil-fil);
    }

    @Override
    public boolean moveColisionaConPieza(int col, int row) { //Comprobar si hay otra pieza en el camino

        // Arriba izquierda
        if(col < this.col && this.fil > fil){
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

        return false;

    }
}