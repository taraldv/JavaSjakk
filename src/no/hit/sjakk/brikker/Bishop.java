/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hit.sjakk.brikker;

import static java.lang.Math.abs;
import no.hit.sjakk.Brett;

public class Bishop extends Brikke {

    public Bishop(Farge farge, Brett brett, String rutenavn) {
        super(farge, brett, BrikkeType.BISHOP, rutenavn, "L");
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
        if (abs(brikkeX - x) == abs(brikkeY - y)) {
            for (int i = 1; i < abs(brikkeX - x); i++) {
                if (x > brikkeX && y > brikkeY && getBrett().getBrikkene()[brikkeX + i][brikkeY + i] != null) {
                    return true;
                } else if (x > brikkeX && y < brikkeY && getBrett().getBrikkene()[brikkeX + i][brikkeY - i] != null) {
                    return true;
                } else if (x < brikkeX && y > brikkeY && getBrett().getBrikkene()[brikkeX - i][brikkeY + i] != null) {
                    return true;
                } else if (x < brikkeX && y < brikkeY && getBrett().getBrikkene()[brikkeX - i][brikkeY - i] != null) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

}
