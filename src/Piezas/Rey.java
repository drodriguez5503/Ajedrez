package Piezas;

import Main.Move;
import Main.Tablero;

import java.awt.image.BufferedImage;

public class Rey extends Pieza{
    public Rey (Tablero tablero, int col, int fil, boolean esBlanca){
        super(tablero);
        this.col = col;
        this.fil = fil;
        this.posX = col* tablero.tamCasilla;
        this.posY = fil* tablero.tamCasilla;

        this.esBlanca = esBlanca;
        this.nombre = "Rey";

        this.sprite = sheet.getSubimage(0, esBlanca ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(sheetScale,sheetScale, BufferedImage.SCALE_SMOOTH);

    }

    @Override
    public boolean isValidMovement(int col, int fil) {
        return Math.abs((col-this.col)*(fil-this.fil)) == 1 || Math.abs(col-this.col) + Math.abs(fil-this.fil) == 1 || canEnroque(col,fil);

    }

    public boolean canEnroque(int col, int fil){

        if(this.fil == fil){

            if(col == 6) {

                Pieza torre = tablero.getPieza(7,fil);
                if(torre != null && torre.esPrimermove && esPrimermove) {
                    return  tablero.getPieza(5,fil) == null &&
                            tablero.getPieza(6,fil) == null &&
                            !tablero.escanerJaques.hayJaque(new Move(tablero,this,5,fil));
                }
            } else if(col == 2) {
                Pieza torre = tablero.getPieza(0,fil);
                if(torre != null && torre.esPrimermove && esPrimermove) {
                    return  tablero.getPieza(3,fil) == null &&
                            tablero.getPieza(2,fil) == null &&
                            tablero.getPieza(1,fil) == null &&
                            !tablero.escanerJaques.hayJaque(new Move(tablero,this,3,fil));
                }

            }
        }

        return false;
    }

}