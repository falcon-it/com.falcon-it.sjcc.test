package packet;

import static org.junit.Assert.*;

import org.junit.Test;

import packet.Packet.DuplicateKeyException;

public class PacketIDTest {

	@Test
	public void test() throws DuplicateKeyException {
		Packet p = new Packet();
		p.add("k5", "ortjskhopgtrk");
		p.add("k1", 788);
		p.add("k2", 788.7878);
		p.add("k4", "gioerhsgirjeiopgjeriop");
		p.add("k3", false);
		int id = p.calculateDynamicID();
		fail("Not yet implemented");
	}

}
