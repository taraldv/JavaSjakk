/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hit.sjakk.brikker;

import no.hit.sjakk.Brett;

public class Knight extends Brikke {

    public Knight(Farge farge, Brett brett, String rutenavn) {
        super(farge, brett, BrikkeType.KNIGHT, rutenavn, "S");
    }

    //Har 8 forskjellige bevegelser som er lovlige
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
                && (sjekk(rutenavn, 1, 2)
                || sjekk(rutenavn, 1, -2)
                || sjekk(rutenavn, -1, 2)
                || sjekk(rutenavn, -1, -2)
                || sjekk(rutenavn, 2, 1)
                || sjekk(rutenavn, 2, -1)
                || sjekk(rutenavn, -2, 1)
                || sjekk(rutenavn, -2, -1));
    }

    private boolean sjekk(String rutenavn, int x, int y) {
        return rutenavn.charAt(0) == getRutenavn().charAt(0) + x && rutenavn.charAt(1) == getRutenavn().charAt(1) + y;
    }
}
