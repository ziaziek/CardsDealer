/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardsdealer;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author pnowicki
 */
public class CardCoder {
    
    public static Card decode(final int n){
        return new Card(CardColour.values()[n/13], CardNumber.values()[n%13]);
    }
    
    public static int code(Card c){
        return 13 * (c.getColour().ordinal())+(c.getNumber().ordinal());
    }
    
    public static Set<Integer> codeAll(final Set<Card> cards){
        Set<Integer> r = new HashSet<>();
        cards.forEach((c) -> {
            r.add(code(c));
        });
        return r;
    }
}
