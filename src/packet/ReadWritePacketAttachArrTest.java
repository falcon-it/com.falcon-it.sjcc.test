package packet;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import org.junit.Test;

import packet.Packet.DuplicateKeyException;
import packet.Packet.KeyNotFoundException;
import packet.Registry.DuplicateTypeIDException;
import packet.Registry.DynamicIDTypeArrayException;
import packet.Registry.IsMultiLevelArrayException;
import packet.Registry.NotTypeIDException;
import packet.binary.BinaryReader;
import packet.binary.BinaryWriter;
import utils.Pair;

public class ReadWritePacketAttachArrTest {

	@Test
	public void test() throws DuplicateKeyException, NullPointerException, IsMultiLevelArrayException, DynamicIDTypeArrayException, CloneNotSupportedException, PacketIOException, NotTypeIDException, KeyNotFoundException, DuplicateTypeIDException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		BinaryWriter  wr = new BinaryWriter();
		Registry reg = new Registry();

		ArrayList<Pair<String, Object>> arr = new ArrayList<>(5);
		arr.add(new Pair<>("k1", 125));
		arr.add(new Pair<>("k6", 'r'));
		arr.add(new Pair<>("k2", "jeij390ut9034"));
		arr.add(new Pair<>("k4", 344.66));
		arr.add(new Pair<>("k5", 545));
		arr.add(new Pair<>("k3", true));

		Packet p = new Packet();
		for(Pair<String, Object> arrI : arr) { p.add(arrI.getFirst(), arrI.getSecond()); }

		reg.addType(p);
		
		Packet pt = (Packet)p.clone();
		
		p.add("packet", pt);
		p.add("packet001", pt);
		
		reg.addType(p);
		
		reg.getSerializerByInstance(p).write(os, p, reg, wr);
	
		ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
		BinaryReader rd = new BinaryReader();
		
		Packet p2 = reg.getSerializerByInstance(p).read(is, reg, rd);
		
		for(Pair<String, Object> arrI : arr) { assertTrue(arrI.getSecond().equals(p2.get(arrI.getFirst()))); }
		for(Pair<String, Object> arrI : arr) { assertTrue(arrI.getSecond().equals(((Packet)p2.get("packet")).get(arrI.getFirst()))); }
		for(Pair<String, Object> arrI : arr) { assertTrue(arrI.getSecond().equals(((Packet)p2.get("packet001")).get(arrI.getFirst()))); }
	}

}
