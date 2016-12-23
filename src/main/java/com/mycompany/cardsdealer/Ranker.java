/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardsdealer;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author pnowicki
 */
public class Ranker {
    
    private final Set<Integer> cardsInt;
    
    public Ranker(final Set<Card> cards){
        cardsInt= new TreeSet<Integer>();
        cards.stream().forEach(c -> cardsInt.add(CardCoder.code(c)));
    }
    
    public int rankCardSet(){
        return 0;
    }
    protected boolean isRoyalFlush(){
        //of all those cards, best set will have to be picked. This will be done from the top of the hierarchy
        //is there a poker? (13, 14, 12, 11, 1)
        int i=0;
        while(i<4 && !cardsInt.containsAll(Arrays.asList(i*13+13,i*13+14,i*13+12,i*13+11,i*13+1))){
            i++;
        }
        return i<4;
    }
    
    protected boolean isStraightFlush(){
        
        return false;
    } 
}
