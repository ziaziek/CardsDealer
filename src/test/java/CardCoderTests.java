/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.cardsdealer.Card;
import com.mycompany.cardsdealer.CardCoder;
import com.mycompany.cardsdealer.CardColour;
import com.mycompany.cardsdealer.CardNumber;
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
 * @author pnowicki
 */
public class CardCoderTests {
    
    public CardCoderTests() {
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
    public void decodeTest(){
        Card[] aces = new Card[4];
        aces[0]=new Card(CardColour.CLUBS, CardNumber.ONE);
        aces[1] = new Card(CardColour.HEART, CardNumber.ONE);
        aces[2] = new Card(CardColour.DIAMOMND, CardNumber.ONE);
        aces[3] = new Card(CardColour.SPADE, CardNumber.ONE);
        Assert.assertEquals(aces[0], CardCoder.decode(0));
        Assert.assertEquals(aces[1], CardCoder.decode(13));
        Assert.assertEquals(aces[2], CardCoder.decode(26));
        Assert.assertEquals(aces[3], CardCoder.decode(39));
        Assert.assertEquals(new Card(CardColour.HEART, CardNumber.THREE), CardCoder.decode(15));
    }
    
    @Test
    public void codeTest(){
        Assert.assertEquals(0, CardCoder.code(new Card(CardColour.CLUBS, CardNumber.ONE)));
        Assert.assertEquals(15, CardCoder.code(new Card(CardColour.HEART, CardNumber.THREE)));
        Assert.assertEquals(51, CardCoder.code(new Card(CardColour.SPADE, CardNumber.KING)));
    }
    
    @Test
    public void codeSetTest(){
        Set<Card> cs = new HashSet<>();
        cs.add(new Card(CardColour.CLUBS, CardNumber.ONE));
        cs.add(new Card(CardColour.HEART, CardNumber.THREE));
        cs.add(new Card(CardColour.CLUBS, CardNumber.SEVEN));
        cs.add(new Card(CardColour.CLUBS, CardNumber.EIGHT));
        Set<Integer> p = CardCoder.codeAll(cs);
        Assert.assertNotNull(p);
        Assert.assertTrue(!p.isEmpty());
        Assert.assertTrue(p.contains(0));
        Assert.assertTrue(p.contains(15));
    }
}
