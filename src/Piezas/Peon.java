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
}
