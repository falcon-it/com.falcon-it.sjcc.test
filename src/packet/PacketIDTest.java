package packet;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import packet.Packet.DuplicateKeyException;
import packet.Registry.DynamicIDTypeArrayException;
import packet.Registry.IsMultiLevelArrayException;

public class PacketIDTest {

	@Test
	public void test() throws DuplicateKeyException, NullPointerException, IsMultiLevelArrayException, DynamicIDTypeArrayException {
		LinkedList<Integer> ids = new LinkedList<>();
		Packet p = new Packet();
		ids.add(p.calculateDynamicID());
		p.add("k5", "ortjskhopgtrk");
		int id = p.calculateDynamicID();
		assertFalse(ids.contains(id));
		ids.add(p.calculateDynamicID());
		p.add("k1", 788);
		id = p.calculateDynamicID();
		assertFalse(ids.contains(id));
		ids.add(p.calculateDynamicID());
		p.add("k2", 788.7878);
		id = p.calculateDynamicID();
		assertFalse(ids.contains(id));
		ids.add(p.calculateDynamicID());
		p.add("k4", "gioerhsgirjeiopgjeriop");
		id = p.calculateDynamicID();
		assertFalse(ids.contains(id));
		ids.add(p.calculateDynamicID());
		p.add("k3", false);
		id = p.calculateDynamicID();
		assertFalse(ids.contains(id));
	}

}
