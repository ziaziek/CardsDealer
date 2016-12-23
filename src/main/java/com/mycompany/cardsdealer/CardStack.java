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
public interface CardStack {
    
    Card[] draw(final int numberOfCards);
    
    void shuffle();
    
    void returnCard(Card c);
}
