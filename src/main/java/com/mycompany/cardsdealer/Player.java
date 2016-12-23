/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardsdealer;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author pnowicki
 */
public class Player {
    
    Set<Card> hand = new TreeSet<>();
    
    public Set<Card> getHand() {
        return hand;
    }
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void fold(){
        hand.clear();
    }
    
    
}
