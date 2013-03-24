package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {

	private Statistics stats;
	Reader readerStub = new Reader() {
		@Override
		public List<Player> getPlayers() {
			ArrayList<Player> players = new ArrayList<Player>();

			players.add(new Player("Semenko", "EDM", 4, 12));
			players.add(new Player("Lemieux", "PIT", 45, 54));
			players.add(new Player("Kurri", "EDM", 37, 53));
			players.add(new Player("Yzerman", "DET", 42, 56));
			players.add(new Player("Gretzky", "EDM", 35, 89));

			return players;
		}
	};
	@Before
	public void setUp() {
		stats = new Statistics(readerStub);
	}

	@Test
	public void testSearching() {
		Player p = stats.search("Gre");
		Assert.assertTrue("Searched name not found though should have.", p.getName().equals("Gretzky") && p.getTeam().equals("EDM"));
		p = stats.search("foobar");
		Assert.assertTrue("Searched name not found though should have.", p == null);
	}

	@Test
	public void testTeamMembers() {
		List<Player> edmonton = stats.team("EDM");
		Assert.assertTrue("team members wrong", edmonton.size()==3);
	}

	@Test
	public void testTopScorers() {
		List<Player> topScorers = stats.topScorers(3);
		Assert.assertTrue(topScorers.size()==3);
		
	}

	@Test
	public void testPlayerData() {
		Player p = stats.search("Kurri");
		p.setAssists(3);
		p.setGoals(1);
		p.setName("Jari Kurri");
		p.setTeam("PIT");

		Assert.assertTrue(p.getAssists() == 3);
		Assert.assertTrue(p.getGoals() == 1);
		Assert.assertTrue(p.getName().equals("Jari Kurri"));
		Assert.assertTrue(p.getTeam().equals("PIT"));
		

		List<Player> topScorers = stats.topScorers(3);
		Assert.assertTrue(!topScorers.contains(p));
	}

	@Test
	public void testToString() {
		Player p = stats.search("Kurri");
		System.out.println("p.toString(): "+p.toString());
		Assert.assertTrue(p.toString().contains("Kurri") && p.toString().contains("EDM"));
	}

}
