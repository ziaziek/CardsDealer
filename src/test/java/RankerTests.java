/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.cardsdealer.Card;
import com.mycompany.cardsdealer.CardColour;
import com.mycompany.cardsdealer.CardNumber;
import com.mycompany.cardsdealer.Ranker;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Przemo
 */
public class RankerTests {
    
    class RankerExt extends Ranker {
        
        public RankerExt(Set<Card> deckCards, Set<Card> handCards) throws Exception {
            super(deckCards, handCards);
        }
        
        @Override
        public int isPair(){
            return super.isPair();
        }

        @Override
        public int isTwoPair() {
            return super.isTwoPair(); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        protected int isThreeOfAKind() {
            return super.isThreeOfAKind(); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        protected int isFourOfAKind() {
            return super.isFourOfAKind(); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        protected int isFullHouse() {
            return super.isFullHouse(); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        protected int isStraight() throws Exception {
            return super.isStraight(); //To change body of generated methods, choose Tools | Templates.
        }
        
        
        
    }
    public RankerTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testIsPair() throws Exception{
        Set<Card> c = new HashSet<>();
        Set<Card> h = new HashSet<>();
        c.add(new Card(CardColour.CLUBS, CardNumber.THREE));
        c.add(new Card(CardColour.DIAMOMND, CardNumber.THREE));
        c.add(new Card(CardColour.CLUBS, CardNumber.FOUR));
        c.add(new Card(CardColour.SPADE, CardNumber.JACK));
        c.add(new Card(CardColour.CLUBS, CardNumber.SIX));
        h.add(new Card(CardColour.CLUBS, CardNumber.KING));
        h.add(new Card(CardColour.SPADE, CardNumber.FIVE));
        RankerExt r = new RankerExt(c, h);
        Assert.assertTrue(r.isPair()>0);
        c.remove(new Card(CardColour.CLUBS, CardNumber.THREE));
        c.add(new Card(CardColour.CLUBS, CardNumber.SEVEN));
        Assert.assertFalse(new RankerExt(c, h).isPair()>0);
    }
    
    @Test
    public void testIsTwoPair() throws Exception{
        Set<Card> c = new HashSet<>();
        Set<Card> h = new HashSet<>();
        c.add(new Card(CardColour.CLUBS, CardNumber.THREE));
        c.add(new Card(CardColour.DIAMOMND, CardNumber.THREE));
        c.add(new Card(CardColour.CLUBS, CardNumber.FOUR));
        c.add(new Card(CardColour.SPADE, CardNumber.JACK));
        c.add(new Card(CardColour.CLUBS, CardNumber.JACK));
        h.add(new Card(CardColour.CLUBS, CardNumber.KING));
        h.add(new Card(CardColour.SPADE, CardNumber.FIVE));
        RankerExt r = new RankerExt(c, h);
        Assert.assertTrue(r.isTwoPair()>0);
        c.remove(new Card(CardColour.CLUBS, CardNumber.JACK));
        c.add(new Card(CardColour.CLUBS, CardNumber.SEVEN));
        Assert.assertFalse(new RankerExt(c, h).isTwoPair()>0);
    }
    
    @Test
    public void testThreeOfAKind() throws Exception{
        Set<Card> c = new HashSet<>();
        Set<Card> h = new HashSet<>();
        c.add(new Card(CardColour.CLUBS, CardNumber.ACE));
        c.add(new Card(CardColour.DIAMOMND, CardNumber.ACE));
        c.add(new Card(CardColour.CLUBS, CardNumber.FOUR));
        c.add(new Card(CardColour.SPADE, CardNumber.JACK));
        c.add(new Card(CardColour.CLUBS, CardNumber.JACK));
        h.add(new Card(CardColour.HEART, CardNumber.NINE));
        h.add(new Card(CardColour.SPADE, CardNumber.ACE));
        RankerExt r = new RankerExt(c, h);
        Assert.assertTrue(r.isThreeOfAKind()>0);
        h.remove(new Card(CardColour.SPADE, CardNumber.ACE));
        h.add(new Card(CardColour.HEART, CardNumber.ACE));
        Assert.assertTrue(r.isThreeOfAKind()>0);
    }
    
    @Test
    public void testFourOfAKind() throws Exception{
        Set<Card> c = new HashSet<>();
        Set<Card> h = new HashSet<>();
        c.add(new Card(CardColour.CLUBS, CardNumber.ACE));
        c.add(new Card(CardColour.DIAMOMND, CardNumber.ACE));
        c.add(new Card(CardColour.CLUBS, CardNumber.FOUR));
        c.add(new Card(CardColour.HEART, CardNumber.ACE));
        c.add(new Card(CardColour.CLUBS, CardNumber.JACK));
        h.add(new Card(CardColour.HEART, CardNumber.NINE));
        h.add(new Card(CardColour.SPADE, CardNumber.ACE));
        RankerExt r = new RankerExt(c, h);
        Assert.assertTrue(r.isFourOfAKind()>0);
    }
    
    
    @Test
    public void testFullHouse() throws Exception{
        Set<Card> c = new HashSet<>();
        Set<Card> h = new HashSet<>();
        c.add(new Card(CardColour.CLUBS, CardNumber.ACE));
        c.add(new Card(CardColour.DIAMOMND, CardNumber.ACE));
        c.add(new Card(CardColour.CLUBS, CardNumber.NINE));
        c.add(new Card(CardColour.HEART, CardNumber.ACE));
        c.add(new Card(CardColour.CLUBS, CardNumber.JACK));
        h.add(new Card(CardColour.HEART, CardNumber.NINE));
        h.add(new Card(CardColour.SPADE, CardNumber.ACE));
        RankerExt r = new RankerExt(c, h);
        Assert.assertTrue(r.isThreeOfAKind()>0);
        Assert.assertTrue(r.isFourOfAKind()>0); //out of interest, this should also pass
    }
    
    @Test
    public void testStraight() throws Exception{
        Set<Card> c = new HashSet<>();
        Set<Card> h = new HashSet<>();
        c.add(new Card(CardColour.CLUBS, CardNumber.TWO));
        c.add(new Card(CardColour.DIAMOMND, CardNumber.QUEEN));
        c.add(new Card(CardColour.CLUBS, CardNumber.THREE));
        c.add(new Card(CardColour.CLUBS, CardNumber.FOUR));
        c.add(new Card(CardColour.CLUBS, CardNumber.JACK));
        h.add(new Card(CardColour.CLUBS, CardNumber.FIVE));
        h.add(new Card(CardColour.CLUBS, CardNumber.SIX));
        RankerExt r = new RankerExt(c, h);
        Assert.assertFalse(r.isStraight()>0);
        c.remove(new Card(CardColour.CLUBS, CardNumber.TWO));
        c.add(new Card(CardColour.HEART, CardNumber.TWO));
        r = new RankerExt(c, h);
        Assert.assertTrue(r.isStraight()>0);
    }
}
