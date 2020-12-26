/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hit.sjakk.brikker;

import no.hit.sjakk.Brett;

public class King extends Brikke {

    public King(Farge farge, Brett brett, String rutenavn) {
        super(farge, brett, BrikkeType.KING, rutenavn, "K");
    }

    //kan gå max et steg i 8 forskjellige retninger
    @Override
    public boolean erLovligTrekk(String rutenavn) {

        //Sjekker om det finnes en brikke i destinasjons ruten og om det er motstander sin brikke
        boolean gyldigDestinasjon;
        try {
            gyldigDestinasjon = getFarge() != getBrett().getBrikke(rutenavn).getFarge();
        } catch (NullPointerException e) {
            gyldigDestinasjon = true;
        }

        //Sjekker om en av de 8 er gyldige
        return gyldigDestinasjon
                && (sjekk(rutenavn, 1, 0)
                || sjekk(rutenavn, 0, 1)
                || sjekk(rutenavn, -1, 0)
                || sjekk(rutenavn, 0, -1)
                || sjekk(rutenavn, 1, 1)
                || sjekk(rutenavn, -1, -1)
                || sjekk(rutenavn, 1, -1)
                || sjekk(rutenavn, -1, 1));
    }

    private boolean sjekk(String rutenavn, int x, int y) {
        return rutenavn.charAt(0) == getRutenavn().charAt(0) + x && rutenavn.charAt(1) == getRutenavn().charAt(1) + y;
    }
}
