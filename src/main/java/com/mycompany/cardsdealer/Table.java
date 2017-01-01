/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardsdealer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author pnowicki
 */
public abstract class Table {
    
    protected CardStack stack;
    protected Set<Card> cardsOnTable;

    public Set<Card> getCardsOnTable() {
        return cardsOnTable;
    }
    
    protected List<Player> players;
    
    public Table(){
        cardsOnTable = new HashSet<>();
    };
    
    public Table(CardStack stack, List<Player> players){
        this();
        this.stack = stack;
        this.players=players;
    }
    
    public abstract void giveCardsToPlayers();
    
    public abstract void giveCardsToTableStack(int numberOfCards);
}
