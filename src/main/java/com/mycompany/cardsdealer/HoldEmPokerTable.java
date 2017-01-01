/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardsdealer;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
            Ranker r = new Ranker(this.getCardsOnTable(), p.getHand());
            r.isRoyalFlush();
        } catch (Exception ex) {
            Logger.getLogger(HoldEmPokerTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
