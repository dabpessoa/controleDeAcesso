package br.com.digitoglobal.accesscontrol.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtils {

	public static String hashMD5String(String string) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(string.getBytes());
        
        byte byteData[] = md.digest();
 
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
	}
	
	public static String generateRandomPassword(int size) {
		String[] carct = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
				"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
				"Y", "Z" };

		String senha = "";
		for (int x = 0; x < size; x++) {
			int j = (int) (Math.random() * carct.length);
			senha += carct[j];
		}

		return senha;
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(hashMD5String("123"));
	}
}
