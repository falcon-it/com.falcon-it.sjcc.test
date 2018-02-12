package packet;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import org.junit.Test;

import packet.Registry.DynamicIDTypeArrayException;
import packet.Registry.IsMultiLevelArrayException;
import packet.Registry.NotTypeIDException;
import packet.binary.BinaryReader;
import packet.binary.BinaryWriter;

public class ReadWriteArrayTypesTest {
	
	@SuppressWarnings("serial")
	static class TST implements Serializable {
		public TST(String _d) { d = _d; }
		String d;
		@Override
		public String toString() {
			return d;
		}
	}

	@Test
	public void test() throws NotTypeIDException, CloneNotSupportedException, PacketIOException, IsMultiLevelArrayException, DynamicIDTypeArrayException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		BinaryWriter  wr = new BinaryWriter();
		Registry reg = new Registry();
		
		int[] arr1 = new int[] {1, 3, 56, -456546};
		TST[] arr2 = new TST[] {new TST("ttu"), new TST("554p"), new TST("990")};
		double[] arr3 = new double[0];
		
		Serialize s = reg.getSerializerByInstance(arr1);
		s.write(os, arr1, reg, wr);
		s.write(os, arr2, reg, wr);
		s.write(os, arr3, reg, wr);
		
		ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
		BinaryReader rd = new BinaryReader();
		
		int[] arr12 = s.read(is, reg, rd);
		TST[] arr22 = s.read(is, reg, rd);
		double[] arr32 = s.read(is, reg, rd);
		for(int i = 0; i < arr12.length; ++i) {
			assertTrue(arr12[i] == arr1[i]);
		}
		for(int i = 0; i < arr22.length; ++i) {
			assertTrue(arr22[i].toString().compareTo(arr2[i].toString()) == 0);
		}
		assertTrue(arr3.length == arr32.length);
	}

}
