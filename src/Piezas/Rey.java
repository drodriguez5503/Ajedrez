package Piezas;

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
        return Math.abs((col-this.col)*(fil-this.fil)) == 1 || Math.abs(col-this.col) + Math.abs(fil-this.fil) == 1;

    }

}