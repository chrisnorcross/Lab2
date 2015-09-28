package pokerBase;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pokerEnums.eRank;
import pokerEnums.eSuit;

public class Card_Test {
	Card c;


	@Before
	public void setUp() throws Exception {
		c = new Card(eSuit.HEARTS, eRank.ACE,false);
	}

	@After
	public void tearDown() throws Exception {
		c = null;
	}

	@Test
	public void rankTest() {
		assertTrue(c.getRank() == eRank.ACE);
	}
	@Test
	public void suitTest(){
		assertTrue(c.getSuit() == eSuit.HEARTS);
	}
	@Test
	public void wildTest(){
		assertTrue(c.getWild() == false);
		c.setWild();
		assertTrue(c.getWild() == true);
	}

}
