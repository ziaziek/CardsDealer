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
        cs.add(new Card(CardColour.CLUBS, CardNumber.ONE));
        cs.add(new Card(CardColour.CLUBS, CardNumber.THREE));
        cs.add(new Card(CardColour.CLUBS, CardNumber.SEVEN));
        cs.add(new Card(CardColour.CLUBS, CardNumber.EIGHT));
        Assert.assertTrue(RankerHelper.sameColour(CardCoder.codeAll(cs)));
        cs.add(new Card(CardColour.DIAMOMND, CardNumber.ONE));
        Assert.assertFalse(RankerHelper.sameColour(CardCoder.codeAll(cs)));
    }
    
    @Test
    public void cardsInSequenceTest(){
        
    }
    
    @Test
    public void getSingleMultiTest(){
        
    }
}
