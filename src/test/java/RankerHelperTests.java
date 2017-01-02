/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.cardsdealer.Card;
import com.mycompany.cardsdealer.CardCoder;
import com.mycompany.cardsdealer.CardColour;
import com.mycompany.cardsdealer.CardNumber;
import com.mycompany.cardsdealer.RankerHelper;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Przemo
 */
public class RankerHelperTests {
    
    public RankerHelperTests() {
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
    public void sameColourTest(){
        Set<Card> cs = new HashSet<>();
        cs.add(new Card(CardColour.CLUBS, CardNumber.ACE));
        cs.add(new Card(CardColour.CLUBS, CardNumber.THREE));
        cs.add(new Card(CardColour.CLUBS, CardNumber.SEVEN));
        cs.add(new Card(CardColour.CLUBS, CardNumber.EIGHT));
        Assert.assertTrue(RankerHelper.sameColour(CardCoder.codeAll(cs)));
        cs.add(new Card(CardColour.DIAMOMND, CardNumber.ACE));
        Assert.assertFalse(RankerHelper.sameColour(CardCoder.codeAll(cs)));
    }
    
    @Test
    public void cardsInSequenceTest() throws Exception{
        Set<Card> cs = new HashSet<>();
        cs.add(new Card(CardColour.CLUBS, CardNumber.SIX));
        cs.add(new Card(CardColour.CLUBS, CardNumber.THREE));
        cs.add(new Card(CardColour.CLUBS, CardNumber.TWO));
        cs.add(new Card(CardColour.CLUBS, CardNumber.FOUR));
        cs.add(new Card(CardColour.DIAMOMND, CardNumber.FIVE));
        Assert.assertTrue(RankerHelper.cardsInSequence(CardCoder.codeAll(cs)));
        cs.remove(new Card(CardColour.CLUBS, CardNumber.TWO));
        cs.add(new Card(CardColour.HEART, CardNumber.EIGHT));
        Assert.assertFalse(RankerHelper.cardsInSequence(CardCoder.codeAll(cs)));
        cs.remove(new Card(CardColour.HEART, CardNumber.EIGHT));
        cs.add(new Card(CardColour.HEART, CardNumber.SEVEN));
        Assert.assertTrue(RankerHelper.cardsInSequence(CardCoder.codeAll(cs)));
    }
    
    @Test
    public void getSingleMultiTest() throws Exception{
        Set<Card> cs = new HashSet<>();
        cs.add(new Card(CardColour.CLUBS, CardNumber.ACE));
        cs.add(new Card(CardColour.DIAMOMND, CardNumber.ACE));
        cs.add(new Card(CardColour.HEART, CardNumber.ACE));
        cs.add(new Card(CardColour.SPADE, CardNumber.ACE));
        Assert.assertEquals(48, RankerHelper.getSingleMulti(CardCoder.codeAll(cs), 4));
        cs.remove(new Card(CardColour.CLUBS, CardNumber.ACE));
        Assert.assertEquals(36 , RankerHelper.getSingleMulti(CardCoder.codeAll(cs), 3));
        try {
            Assert.assertNotEquals(4 , RankerHelper.getSingleMulti(CardCoder.codeAll(cs), 4));
            fail("This should fail. Wrong number of cards.");
        } catch(Exception e){
            Assert.assertTrue(true);
        }
    }
}
