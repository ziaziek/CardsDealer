/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardsdealer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provide a set of cards on the table and the hand
 *
 * @author pnowicki
 */
public class Ranker {

    private final Set<Integer> cardsInt; //this set should always have size 7
    private final Set<Integer> kickers;
    private final Set<Integer> deck;
    private final List<Set<Integer>> fiveCardPermutations;

    public Ranker(final Set<Card> deckCards, final Set<Card> handCards) throws Exception {
        if (deckCards == null || handCards == null || deckCards.size() != 5  || handCards.size()!=2) {
            throw new Exception("The size of the cards stack should be 7.");
        } else {
            cardsInt = new TreeSet<>();
            deck = new TreeSet<>();
            kickers=new TreeSet<>();
            deckCards.stream().forEach(c -> deck.add(CardCoder.code(c)));
            handCards.stream().forEach(c -> kickers.add(CardCoder.code(c)));
            cardsInt.addAll(deck);
            cardsInt.addAll(kickers);
            fiveCardPermutations = buildPermutations(cardsInt, 5);
        }

    }

    public int rankCardSet() {
        return 0;
    }

    protected int isRoyalFlush() {
        //of all those cards, best set will have to be picked. This will be done from the top of the hierarchy
        //is there a poker? (13, 14, 12, 11, 1)
        int i = 0;
        while (i < 4 && !cardsInt.containsAll(Arrays.asList(i * 13 + 13, i * 13 + 14, i * 13 + 12, i * 13 + 11, i * 13 + 1))) {
            i++;
        }
        return i < 4 ? 100 : 0;
    }

    protected int isStraightFlush() {
        return RankerHelper.sameColour(cardsInt) && fiveCardPermutations.stream().anyMatch(s -> {
            try {
                RankerHelper.cardsInSequence(s);
            } catch (Exception ex) {
                Logger.getLogger(Ranker.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }) ? 90 : 0;
    }

    protected int isFourOfAKind() {
        List<Set<Integer>> fours = buildPermutations(cardsInt, 4);
        int value = 0;
        boolean isFour = false;
        for (Set<Integer> set : fours) {
            int s0 = 0, s1 = 0;
            int p = 0;
            for (Integer n : set) {
                p = n % 13;
                if (s0 == 0) {
                    s0 = set.size() * p;
                }
                s1 += p;
            }
            if (s1 == s0) {
                value = p;
                isFour = true;
            }
            if (isFour) {
                break;
            }
        }
        return value == 0 ? value : 4 * (10 + value); //min = 44 max = 80
    }

    protected int isFullHouse() {
        //first check if there is a three of a kind and if there is a two of a kind, and there is no 4 of a kind for every 5-card permutation. 
        //Then make sure that getSingleMulti(permutation[i]) == 0 - all of the cards are not equal, so there is no 5 of a kind.

        return 6;
    }

    protected int isFlush() {
        return 5;
    }

    protected int isStraight() {
        return 4;
    }

    protected int isThreeOfAKind() {

        try {
            return RankerHelper.getSingleMulti(cardsInt, 3);
        } catch (Exception ex) {
            Logger.getLogger(Ranker.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    protected int isTwoPair() {
        TreeSet<Integer> cardsOrdered = new TreeSet<>();
        cardsInt.forEach((p) -> {
            cardsOrdered.add(p%13);
        });
        if(cardsOrdered.stream().distinct().count()==5){//We've got two pairs
            int v = -1; int np=0;
        for(int i: cardsOrdered){
            if(i==v){
                np+=2*v;
                v=0;
            } else {
                v=i;
            }
        }
        }
        
        return 2;
    }

    protected int isPair() {
        try {
            return RankerHelper.getSingleMulti(cardsInt, 2);
        } catch (Exception ex) {
            Logger.getLogger(Ranker.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    private List<Set<Integer>> buildPermutations(Set<Integer> cards, int setSize) {
        List<Set<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < (1 << cards.size()); i++) {
            Set processed = processSubset(cardsInt, i);
            if (processed.size() == setSize) {
                ret.add(processed);
            }
        }
        return ret;
    }

    private Set<Integer> processSubset(Set<Integer> set, int signature) {
        Set<Integer> ret = new TreeSet<>();
        int mask = 1;
        int[] pit = new int[set.size() - 1];
        Iterator<Integer> piter = set.iterator();
        int v = 0;
        while (v != pit.length && piter.hasNext()) {
            pit[v] = piter.next();
        }
        for (int i = 0; i < set.size(); i++) {
            if ((mask & signature) > 0) {
                ret.add(pit[i]);
            }
            mask = mask << 1;
        }
        return ret;
    }
}
