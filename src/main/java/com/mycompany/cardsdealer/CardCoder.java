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
public class CardCoder {
    
    public static Card decode(final int n){
        return new Card(CardColour.values()[n/13], CardNumber.values()[n%13]);
    }
    
    public static int code(Card c){
        return 13 * (c.getColour().ordinal())+(c.getNumber().ordinal());
    }
}
