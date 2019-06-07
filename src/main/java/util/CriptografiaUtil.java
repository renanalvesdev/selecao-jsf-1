package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptografiaUtil {

	public static String criptografaMd5(String texto) {
		try {
			MessageDigest m;
			m = MessageDigest.getInstance("MD5");
			m.update(texto.getBytes(), 0, texto.length());
			return new BigInteger(1, m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}

}
