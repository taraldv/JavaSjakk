/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hit.sjakk.brikker;

import no.hit.sjakk.Brett;

public class Rook extends Brikke {

    public Rook(Farge farge, Brett brett, String rutenavn) {
        super(farge, brett, BrikkeType.ROOK, rutenavn,"T");
    }

    //kan gå i 4 forskjellige retninger, kan ikke hoppe over brikker
    @Override
    public boolean erLovligTrekk(String rutenavn) {
        
        //Sjekker om det finnes en brikke i destinasjons ruten og om det er motstander sin brikke
        boolean gyldigDestinasjon;
        try {
            gyldigDestinasjon = getFarge() != getBrett().getBrikke(rutenavn).getFarge();
        } catch (NullPointerException e) {
            gyldigDestinasjon = true;
        }
        
        return gyldigDestinasjon && !blokkertAvBrikke(rutenavn.charAt(1) - 49, rutenavn.charAt(0) - 97);
    }

    private boolean blokkertAvBrikke(int x, int y) {
        int brikkeX = getRutenavn().charAt(1) - 49;
        int brikkeY = getRutenavn().charAt(0) - 97;
        //Sjekker retning nord
        if (brikkeX < x && brikkeY == y) {
            for (int i = brikkeX + 1; i < x; i++) {
                if (getBrett().getBrikkene()[i][y] != null) {
                    return true;
                }
            }
        //Sjekker retning øst
        } else if (brikkeX == x && brikkeY < y) {
            for (int j = brikkeY + 1; j < y; j++) {
                if (getBrett().getBrikkene()[x][j] != null) {
                    return true;
                }
            }
        //Sjekker retning sør
        } else if (brikkeX > x && brikkeY == y) {
            for (int k = brikkeX - 1; k > x; k--) {
                if (getBrett().getBrikkene()[k][y] != null) {
                    return true;
                }
            }
        //Sjekker retning vest
        } else if (brikkeX == x && brikkeY > y) {
            for (int l = brikkeY - 1; l > y; l--) {
                if (getBrett().getBrikkene()[x][l] != null) {
                    return true;
                }
            }
        } else {
            //Ugyldig retning
            return true;
        }
        //Ingenting mellom brikken og destinasjonen
        return false;
    }

}
