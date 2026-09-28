package Piezas;

import Main.Tablero;

import java.awt.image.BufferedImage;

public class Caballo extends Pieza{
    public Caballo(Tablero tablero, int col, int fil, boolean esBlanca){
        super(tablero);
        this.col = col;
        this.fil = fil;
        this.posX = col* tablero.tamCasilla;
        this.posY = fil* tablero.tamCasilla;

        this.esBlanca = esBlanca;
        this.nombre = "Caballo";

        this.sprite = sheet.getSubimage(3 * sheetScale, esBlanca ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(sheetScale,sheetScale, BufferedImage.SCALE_SMOOTH);

    }

    public boolean isValidMovement(int col, int fil){
        return Math.abs(col-this.col) * Math.abs(fil-this.fil) == 2;
    } //Ver si el movimiento es válido

}
