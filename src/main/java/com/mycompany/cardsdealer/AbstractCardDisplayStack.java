/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardsdealer;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author pnowicki
 */
public abstract class AbstractCardDisplayStack implements CardStack {

    
    protected Queue<Card> cards;

    public Queue<Card> getCards() {
        return cards;
    }
    
    public AbstractCardDisplayStack(){
        init();
    }
    /**
     * Initialises the stack and its card stack
     */
    protected abstract void init();
    
    @Override
    public Card[] draw(int numberOfCards) {
        int maxDraw = cards.size()>numberOfCards ? numberOfCards : cards.size();
        Card[] ret = new Card[maxDraw];
        for(int i=0; i<maxDraw; i++){
            ret[i]=cards.poll();
        }
        return ret;
    }

    /**
     * Shuffles cards within the current stack
     */
    @Override
    public void shuffle() {
        LinkedList<Card> cc = new LinkedList<>(cards);
        Collections.shuffle(cc);
        cards=cc;
    }

    /**
     * Returns the card to the stack
     * @param c 
     */
    @Override
    public void returnCard(Card c) {
        if(c!=null){
            cards.offer(c);
        }
    }
    
    
}
