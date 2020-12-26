/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hit.sjakk;

import no.hit.sjakk.brikker.Farge;
import no.hit.sjakk.brikker.Brikke;
import no.hit.sjakk.brikker.Bishop;
import no.hit.sjakk.brikker.King;
import no.hit.sjakk.brikker.Knight;
import no.hit.sjakk.brikker.Pawn;
import no.hit.sjakk.brikker.Queen;
import no.hit.sjakk.brikker.Rook;

public class Brett {

    public static final int BRETTSTORRELSE = 8;
    private final int spillNr;
    private final Brikke[][] brikkene = new Brikke[BRETTSTORRELSE][BRETTSTORRELSE];

    public Brett(int nyttSpillNr) {
        spillNr = nyttSpillNr;
        fyllInnArray();
    }

    public int getSpillNr() {
        return spillNr;
    }

    public Brikke[][] getBrikkene() {
        return brikkene;
    }

    //Sjekker om rutenavn er lovlig, fra 1->8 og a->h
    public static boolean erLovligRutenavn(String rutenavn) {
        if (rutenavn.length() == 2) {
            return (rutenavn.charAt(0) >= 97
                    && rutenavn.charAt(0) <= 104
                    && rutenavn.charAt(1) >= 49
                    && rutenavn.charAt(1) <= 56);
        } else {
            return false;
        }
    }

    public Brikke getBrikke(String rutenavn) {
        return brikkene[rutenavn.charAt(1) - 49][rutenavn.charAt(0) - 97];
    }

    /*  Sjekker først om brikken kan flyttes. Inkludert i sjekk er om motstanders brikke kan tas.
    *   Hvis det er lovlig trekk settes tilRute sin plassering i array til null i tilfelle det var en motstander sin brikke der.
    *   Så settes tilRute sin plassering i array til å referer til samme brikke som er i fraRute.
    *   Til slutt settes fraRute sin plassering i array til null
    */
    public boolean flyttBrikke(String fraRute, String tilRute) {
        if (getBrikke(fraRute).flyttTil(tilRute)) {
            brikkene[tilRute.charAt(1) - 49][tilRute.charAt(0) - 97] = null;
            brikkene[tilRute.charAt(1) - 49][tilRute.charAt(0) - 97] = getBrikke(fraRute);
            brikkene[fraRute.charAt(1) - 49][fraRute.charAt(0) - 97] = null;
            return true;
        } else {
            return false;
        }
    }

    //Legger inn alle brikkene i start posisjonen
    private void fyllInnArray() {
        for (int i = 0; i < BRETTSTORRELSE; i++) {
            brikkene[1][i] = new Pawn(Farge.WHITE, this, (char) (i + 97) + "" + 2);
            brikkene[6][i] = new Pawn(Farge.BLACK, this, (char) (i + 97) + "" + 7);
            switch (i) {
                case 0:
                case 7:
                    brikkene[0][i] = new Rook(Farge.WHITE, this, (char) (i + 97) + "" + 1);
                    brikkene[7][i] = new Rook(Farge.BLACK, this, (char) (i + 97) + "" + 8);
                    break;
                case 1:
                case 6:
                    brikkene[0][i] = new Knight(Farge.WHITE, this, (char) (i + 97) + "" + 1);
                    brikkene[7][i] = new Knight(Farge.BLACK, this, (char) (i + 97) + "" + 8);
                    break;
                case 2:
                case 5:
                    brikkene[0][i] = new Bishop(Farge.WHITE, this, (char) (i + 97) + "" + 1);
                    brikkene[7][i] = new Bishop(Farge.BLACK, this, (char) (i + 97) + "" + 8);
                    break;
                case 3:
                    brikkene[0][i] = new Queen(Farge.WHITE, this, (char) (i + 97) + "" + 1);
                    brikkene[7][i] = new Queen(Farge.BLACK, this, (char) (i + 97) + "" + 8);
                    break;
                case 4:
                    brikkene[0][i] = new King(Farge.WHITE, this, (char) (i + 97) + "" + 1);
                    brikkene[7][i] = new King(Farge.BLACK, this, (char) (i + 97) + "" + 8);
                    break;
                default:
                    break;
            }
        }
    }
}
