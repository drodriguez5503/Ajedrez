package Piezas;

import Main.Tablero;

import java.awt.image.BufferedImage;

public class Peon  extends Pieza{
    public Peon (Tablero tablero, int col, int fil, boolean esBlanca){
        super(tablero);
        this.col = col;
        this.fil = fil;
        this.posX = col* tablero.tamCasilla;
        this.posY = fil* tablero.tamCasilla;

        this.esBlanca = esBlanca;
        this.nombre = "Peón";

        this.sprite = sheet.getSubimage(5 * sheetScale, esBlanca ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(sheetScale,sheetScale, BufferedImage.SCALE_SMOOTH);

    }

    @Override
    public boolean isValidMovement(int col, int fil) {
        int indiceColor = esBlanca ? 1 : -1 ;

        // Peon 1
        if(this.col == col && fil == this.fil - indiceColor && tablero.getPieza(col,fil) == null) {
            return true;
        }

        // Peon 2
        if(esPrimermove && this.col == col && fil == this.fil - 2*indiceColor && tablero.getPieza(col,fil) == null && tablero.getPieza(col,fil+indiceColor) == null) {
            return true;
        }

        // Captura Izquierda
        if(col == this.col-1 && fil == this.fil - indiceColor && tablero.getPieza(col,fil) != null) {
            return true;
        }

        // Captura derecha
        if(col == this.col + 1 && fil == this.fil - indiceColor && tablero.getPieza(col,fil) != null) {
            return true;
        }

        return false;
    }
}
