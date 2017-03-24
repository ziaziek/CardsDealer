/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardsdealer;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 *
 * @author Przemo
 */
public class RankerHelper {
    
    /**
     * Produces a sum of the cards value if all provided have equal value
     * @param pair a set of cards to search
     * @param n number of cards to look up (2 - a pair, 3- three, etc.)
     * @return
     */
    public static int getSingleMulti(Set<Integer> pair, int n){
        if(pair.size()==n && pair.stream().mapToInt(x -> x%13).distinct().count()==1){
            int p = pair.stream().mapToInt(i->i%13).sum();
            return p;
        } else {
            return 0;
        }
    }
    
    /**
     * Check if all cards in the set are of the same suit
     * @param cards
     * @return 
     */
    public static boolean sameColour(Set<Integer> cards){
        return cards.stream().mapToInt(i->i/13).distinct().count()==1;
    }
    
    /**
     * Checks if the given set of 5 cards contains cards in sequence
     * @param cards
     * @return
     * @throws Exception 
     */
    public static boolean cardsInSequence(Set<Integer> cards) throws Exception{
        if(cards.size()==5){
            TreeSet<Integer> sint = new TreeSet(cards.stream().map(i->i%13).collect(Collectors.toSet()));
            int p = -1;
            for(Integer i: sint){
                if(p!=-1 && (i-p)!=1){
                    return false;
                } else {
                    p=i;
                }
            }
            return sint.size()==cards.size(); //return only this condition as the sequence has already been confirmed or denied.
        } else {
            throw new Exception("There must be 5 cards examined for a sequence.");
        }
    }
}
