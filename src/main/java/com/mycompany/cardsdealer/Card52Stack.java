/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardsdealer;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Przemo
 */
public class Card52Stack extends AbstractCardDisplayStack {

    @Override
    protected void init() {
        cards = prepareFullStack();
        shuffle();
    }
    
    private Queue<Card> prepareFullStack(){
        Queue stack = new LinkedList();
        for(CardColour col: CardColour.values()){
            for (CardNumber num : CardNumber.values()){
                stack.offer(new Card(col,num));
            }
        }
        return stack;
    }
    
}
