/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hit.sjakk.brikker;

import no.hit.sjakk.Brett;

public class Pawn extends Brikke {

    public Pawn(Farge farge, Brett brett, String rutenavn) {
        super(farge, brett, BrikkeType.PAWN, rutenavn,"B");
    }

    //Har 4 forskellige bevegelser per farge siden den ikke kan gå bakover, så totalt 8
    //En av bevegelsene er kun lovlig fra start posisjonen, og kun 2 av de kan ta motstandere
    @Override
    public boolean erLovligTrekk(String rutenavn) {
        return (pawnFramEn(rutenavn) || pawnFramTo(rutenavn) || pawnTar(rutenavn));
    }

    //Sjekker om et steg fremover er lovlig
    private boolean pawnFramEn(String rutenavn) {
        if (getFarge() == Farge.WHITE
                && rutenavn.charAt(0) == getRutenavn().charAt(0)
                && rutenavn.charAt(1) == getRutenavn().charAt(1) + 1
                && getBrett().getBrikke(rutenavn) == null) {
            return true;
        } else if (getFarge() == Farge.BLACK
                && rutenavn.charAt(0) == getRutenavn().charAt(0)
                && rutenavn.charAt(1) == getRutenavn().charAt(1) - 1
                && getBrett().getBrikke(rutenavn) == null) {
            return true;
        } else {
            return false;
        }
    }
    
    //Sjekker om to steg fremover er lovlig
    private boolean pawnFramTo(String rutenavn) {
        if (getFarge() == Farge.WHITE
                && rutenavn.charAt(0) == getRutenavn().charAt(0)
                && getRutenavn().charAt(1) == '2'
                && rutenavn.charAt(1) == getRutenavn().charAt(1) + 2
                && getBrett().getBrikke(rutenavn) == null) {
            return true;
        } else if (getFarge() == Farge.BLACK
                && rutenavn.charAt(0) == getRutenavn().charAt(0)
                && getRutenavn().charAt(1) == '7'
                && rutenavn.charAt(1) == getRutenavn().charAt(1) - 2
                && getBrett().getBrikke(rutenavn) == null) {
            return true;
        } else {
            return false;
        }
    }

    //Sjekker om det er lovlig og ta et steg diagonalt og ta en motstander
    private boolean pawnTar(String rutenavn) {
        try {
            if (getFarge() == Farge.WHITE
                    && (rutenavn.charAt(0) == getRutenavn().charAt(0) + 1 || rutenavn.charAt(0) == getRutenavn().charAt(0) - 1)
                    && rutenavn.charAt(1) == getRutenavn().charAt(1) + 1
                    && getBrett().getBrikke(rutenavn).getFarge() == Farge.BLACK) {
                return true;
            } else if (getFarge() == Farge.BLACK
                    && (rutenavn.charAt(0) == getRutenavn().charAt(0) + 1 || rutenavn.charAt(0) == getRutenavn().charAt(0) - 1)
                    && rutenavn.charAt(1) == getRutenavn().charAt(1) - 1
                    && getBrett().getBrikke(rutenavn).getFarge() == Farge.WHITE) {
                return true;
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            return false;
        }
    }
}
