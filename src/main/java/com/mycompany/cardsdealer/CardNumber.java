/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardsdealer;

/**
 *
 * @author pnowicki
 */
public enum CardNumber {
    
    ONE("A"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"),
    JACK("J"), QUEEN("Q"), KING("K") ;
    
    private final String name;

    public String getName() {
        return name;
    }
    
    CardNumber(String name){
        this.name=name;
    }
}
