/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hit.sjakk.brikker;

import no.hit.sjakk.Brett;

public abstract class Brikke implements Lovlig {

    private final Farge farge;
    private final Brett brett;
    private final BrikkeType brikkeType;
    private String rutenavn;
    private final String brikkenavn;

    public Brikke(Farge farge, Brett brett, BrikkeType brikkeType, String rutenavn, String brikkenavn) {
        this.farge = farge;
        this.brett = brett;
        this.brikkeType = brikkeType;
        this.rutenavn = rutenavn;
        this.brikkenavn = brikkenavn;
    }

    public String getBrikkenavn() {
        return brikkenavn;
    }

    public String getRutenavn() {
        return rutenavn;
    }

    public void setRutenavn(String rutenavn) {
        this.rutenavn = rutenavn;
    }

    public Farge getFarge() {
        return farge;
    }

    public Brett getBrett() {
        return brett;
    }

    public BrikkeType getBrikkeType() {
        return brikkeType;
    }

    @Override
    public abstract boolean erLovligTrekk(String rutenavn);

    public boolean flyttTil(String rutenavn) {
        if (erLovligTrekk(rutenavn)) {
            this.rutenavn = rutenavn;
            return true;
        } else {
            return false;
        }
    }
}
