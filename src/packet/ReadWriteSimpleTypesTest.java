package packet;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.Test;

import packet.binary.BinaryReader;
import packet.binary.BinaryWriter;

public class ReadWriteSimpleTypesTest {

	@Test
	public void test() throws PacketIOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		BinaryWriter  wr = new BinaryWriter();
		
		String s1 = "my test 00";
		byte[] barr1 = s1.getBytes();
		int i1 = barr1.length / 2;
		boolean b1 = true;
		byte bt1 = 42;
		char c1 = 'h';
		double d1 = 3887.98;
		float f1 = 43.89f;
		long l1 = 34678563;
		short st1 = 345;
		
		for(int i = 0; i < 10; ++i) {
			wr.write(os, barr1, 0, i1);
			wr.write(os, barr1, i1, barr1.length - i1);
			wr.writeBoolean(os, b1);
			wr.writeByte(os, bt1);
			wr.writeChar(os, c1);
			wr.writeDouble(os, d1);
			wr.writeFloat(os, f1);
			wr.writeInt(os, i1);
			wr.writeLong(os, l1);
			wr.writeShort(os, st1);
			wr.writeString(os, s1);
			wr.writeObject(os, s1);
		}
		
		ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
		BinaryReader rd = new BinaryReader();
		
		for(int ii = 0; ii < 10; ++ii) {
		
			byte[] barr2 = new byte[barr1.length];
			
			rd.readBytes(is, barr2, 0, i1);
			rd.readBytes(is, barr2, i1, barr1.length - i1);
			boolean b2 = rd.readBoolean(is);
			byte bt2 = rd.readByte(is);
			char c2 = rd.readChar(is);
			double d2 = rd.readDouble(is);
			float f2 = rd.readFloat(is);
			int i2 = rd.readInt(is);
			long l2 = rd.readLong(is);
			short st2 = rd.readShort(is);
			String s2 = rd.readString(is);
			String so2 = rd.readObject(is);
			
			for(int i = 0; i < barr1.length; ++i) {
				assertTrue(barr1[i] == barr2[i]);
			}
			assertTrue(b1 == b2);
			assertTrue(bt1 == bt2);
			assertTrue(c1 == c2);
			assertTrue(d1 == d2);
			assertTrue(f1 == f2);
			assertTrue(i1 == i2);
			assertTrue(l1 == l2);
			assertTrue(st1 == st2);
			assertTrue(s1.compareTo(s2) == 0);
			assertTrue(s1.compareTo(so2) == 0);
		}
	}

}
