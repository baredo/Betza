package Betza;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import betza.Betza;
import utils.Notation;
import view.Position;

@TestInstance(Lifecycle.PER_CLASS)
class test {
	@BeforeAll
	void initParam() {
		Notation.Init();
	}
	
	@Test
	void QuantityMovesF() {
			assertEquals(Betza.parseBetza("F").size(), 4);
	}
	@Test
	void QuantityMovesW() {
			assertEquals(Betza.parseBetza("W").size(), 4);
	}
	@Test
	void QuantityMovesfW() {
			assertEquals(Betza.parseBetza("fW").size(), 1);
	}
	@Test
	void QuantityMovesfrW() {
			assertEquals(Betza.parseBetza("frW").size(), 2);
	}
	@Test
	void QuantityMovesblW() {
			assertEquals(Betza.parseBetza("lbW").size(), 2);
	}
	@Test
	void QuantityMovesWW() {
			assertEquals(Betza.parseBetza("WW").size(), 4);
	}
	@Test
	void QuantityMovesW0() {
			assertEquals(Betza.parseBetza("W0").size(), 4);
	}
	@Test
	void QuantityMovesF0() {
			assertEquals(Betza.parseBetza("F0").size(), 4);
	}
	@Test
	void QuantityMovesWWFF() {
			assertEquals(Betza.parseBetza("WWFF").size(), 8);
	}
	@Test
	void QuantityMovesfFvW3() {
		assertEquals(Betza.parseBetza("fFvW3").size(), 8);
	}
	@Test
	void QuantityMovessWWfWWbFFfF5bW5() {
		assertEquals(Betza.parseBetza("sWWfWWbFFfF5bW5").size(), 20);
	}
}
