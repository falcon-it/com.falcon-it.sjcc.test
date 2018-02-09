package packet;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;

import org.junit.Test;

import packet.Registry.NotTypeIDException;
import packet.binary.BinaryWriter;

public class ReadWriteArrayTypesTest {

	@Test
	public void test() throws NotTypeIDException, CloneNotSupportedException, PacketIOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		BinaryWriter  wr = new BinaryWriter();
		Registry reg = new Registry();
		
		int[] arr1 = new int[] {1, 3, 56, -456546};
		int[][] arr2 = new int[][] {{454, 677, 44}, {-56, -6728, 0, 969, -456}};
		
		Serialize s = reg.getSerializerByInstance(arr1);
		s.write(os, arr1, reg, wr);
		s.write(os, arr2, reg, wr);
		
		byte[] n = os.toByteArray();
		
		int[] arr12 = arr1;
		int[][] arr22 = arr2;
		for(int i = 0; i < arr12.length; ++i) {
			assertTrue(arr12[i] == arr1[i]);
		}
		for(int i = 0; i < arr22.length; ++i) {
			for(int j = 0; j < arr22[i].length; ++j) {
				assertTrue(arr22[i][j] == arr2[i][j]);
			}
		}
	}

}
