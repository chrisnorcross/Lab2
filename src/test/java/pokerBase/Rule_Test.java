package pokerBase;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pokerEnums.eGame;

public class Rule_Test {
	Rule r;

	@Test
	public void FiveStudtest() {
		r = new Rule(eGame.FiveStud);
		assertTrue(r.GetMaxNumberOfPlayers()==4);
		assertTrue(r.GetNumberOfCards()==5);
		assertTrue(r.GetNumberOfJokers()==0);
		assertTrue(r.GetCommunityCardsCount()==0);
		r = null;
	}
	@Test
	public void OmahaTest(){
		r = new Rule(eGame.Omaha);
		assertTrue(r.GetMaxNumberOfPlayers()==6);
		assertTrue(r.GetNumberOfCards()==4);
		assertTrue(r.GetNumberOfJokers()==0);
		assertTrue(r.GetCommunityCardsCount()==5);
	}

}
