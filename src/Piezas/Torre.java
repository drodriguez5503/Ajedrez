package Piezas;

import Main.Tablero;
import java.awt.image.BufferedImage;

public class Torre extends Pieza{
    public Torre (Tablero tablero, int col, int fil, boolean esBlanca){
        super(tablero);
        this.col = col;
        this.fil = fil;
        this.posX = col* tablero.tamCasilla;
        this.posY = fil* tablero.tamCasilla;

        this.esBlanca = esBlanca;
        this.nombre = "Peón";

        this.sprite = sheet.getSubimage(4 * sheetScale, esBlanca ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(sheetScale,sheetScale, BufferedImage.SCALE_SMOOTH);

    }

    public boolean isValidMovement(int col, int fil) {
        return (this.col == col || this.fil == fil);
    } //Ver si el movimiento es válido

    public boolean moveColisionaConPieza(int col, int fil){ //Comprobar si hay otra pieza en el camino

        //Izquierda
        if(this.col>col){
            for(int c = this.col - 1; c > col; c--){
                if(tablero.getPieza(c,this.fil) != null){
                    return true;
                }
            }
        }

        //Derecha
        if(this.col<col){
            for(int c = this.col + 1; c < col; c++){
                if(tablero.getPieza(c,this.fil) != null){
                    return true;
                }
            }
        }

        //Arriba
        if(this.fil>fil){
            for(int f = this.fil - 1; f > fil; f--){
                if(tablero.getPieza(this.col,f) != null){
                    return true;
                }
            }
        }

        //Abajo
        if(this.fil<fil){
            for(int f = this.fil + 1; f < fil; f++){
                if(tablero.getPieza(this.col,f) != null){
                    return true;
                }
            }
        }

        return false;
    }
}