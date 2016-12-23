/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardsdealer;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author pnowicki
 */
public class HoldEmPokerTable extends Table {

    @Override
    public void giveCardsToPlayers() {
        for(Player p: players){
            p.getHand().addAll(Arrays.asList(stack.draw(2)));
        }
    }

    @Override
    public void giveCardsToTableStack(int numberOfCards) {
        cardsOnTable.addAll(Arrays.asList(stack.draw(numberOfCards)));
    }
    
    //The winner for the game
    public Player assessTheWinner(){
        PriorityQueue<RankedPlayer> rankedPlayers = new PriorityQueue<>();
        if(cardsOnTable.size()==5){
            for(Player p: players){
                if(!p.getHand().isEmpty() && p instanceof RankedPlayer){
                    rankPlayer((RankedPlayer) p);
                    rankedPlayers.add((RankedPlayer) p);
                }
            }
        }
        return null;
    }

    private void rankPlayer(RankedPlayer p) {
        Set<Card> allDeck = new TreeSet<>();
        allDeck.addAll(p.getHand());
        allDeck.addAll(this.getCardsOnTable());
        Ranker r = new Ranker(allDeck);
        r.isRoyalFlush();
    }
}
