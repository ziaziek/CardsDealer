/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardsdealer;

import java.util.Objects;

/**
 *
 * @author pnowicki
 */
public class Card implements Comparable<Card> {
    
    private final CardColour colour;

    public CardColour getColour() {
        return colour;
    }

    public CardNumber getNumber() {
        return number;
    }
    private final CardNumber number;
    
    public Card(final CardColour colour, final CardNumber cardNumber){
        this.colour=colour;
        this.number=cardNumber;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Card) && ((Card)obj).getColour()==this.getColour() && ((Card)obj).getNumber()==this.getNumber();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.colour);
        hash = 71 * hash + Objects.hashCode(this.number);
        return hash;
    }

    @Override
    public int compareTo(Card o) {
        return Integer.compare(CardCoder.code(this), CardCoder.code(o));
    }
    
    
}
