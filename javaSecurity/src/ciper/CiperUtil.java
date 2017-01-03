package ciper;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CiperUtil {
	private static byte[] randomKey;

	public static byte[] genRandomKey(String algo) throws Exception {
		//AES의 알고이즘의 키 크기 :128비트 ~192비트 
		KeyGenerator keyGen = KeyGenerator.getInstance(algo);
		//키의 크기 지정
		//keyGen.init(128);//128비트 크기의 키 생성 
		SecretKey key = keyGen.generateKey();
		return key.getEncoded();
	}
	//암호문을 생성해주는 메서드
	public static String encrypt(String msg) {
		Cipher cipher;
		byte[] ciperMsg = new byte[1024];
		try{
			/*
			 * AES : 암호 알고리즘
			 *       블럭 알고리즘의 종류(64비트씩 나누어서 알고리즘을 사용 )
			 * CBC(CipherBlock Chaining):앞블럭의 암호문이 뒤블럭의 
			 *                           암호화키에 추가 되는 그런 알고리즘 방식
			 * PKCS5Padding: 64비트 단위로 암호화하시 마지막 블럭을 패딩 을 통해서 64비트로 채워준다 .                 
			 */      
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			//대칭키 자동생성
			randomKey = genRandomKey("AES");
			Key key = new SecretKeySpec(randomKey, "AES");
			//초기화 백터(initial vector):체이닝에 사용하는 
			byte[] iv = new byte[]{
					(byte)0x8E,0x12,0x39,(byte)0x9c,
					 0x07,     0x72,0x6F,(byte)0x5A,
					(byte)0x8E,0x12,0x39,(byte)0x9C,
					 0x07,     0x72,0x6F,(byte)0x5A
			};
			AlgorithmParameterSpec paramSpec =
					new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE,key,paramSpec);
			ciperMsg = cipher.doFinal(padString(msg).getBytes());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return bytesToHex(ciperMsg).trim();
	}
	private static String bytesToHex(byte[] ciperMsg) {
		if(ciperMsg == null) return null;
		int len = ciperMsg.length;
		String str="";
		for(int i=0; i < len;i++){
			if((ciperMsg[i]&0xFF) <16){
				str +="0"+Integer.toHexString(ciperMsg[i]&0xFF);
			}else{
				str +=Integer.toHexString(ciperMsg[i]&0xFF);
			}
		}
		return str;
	}
	private static String padString(String msg) {
		char paddingChar =' ';
		int size = 16;
		int x = msg.length()%size;
		int padLength = size - x;
		for(int i = 0; i< padLength ; i++)
			msg +=paddingChar;
		return msg;
	}
	public static String decrypt(String ciperText) {
		Cipher cipher;
		byte[] plainMsg = new byte[1024];
		try{
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			Key key = new SecretKeySpec(randomKey, "AES");
			byte[] iv = new byte[]{
					(byte)0x8E,0x12,0x39,(byte)0x9C,
					0x07,      0x72,0x6F,(byte)0x5A,
					(byte)0x8E,0x12,0x39,(byte)0x9C,
					0x07,      0x72,0x6F,(byte)0x5A
			};
			AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
		cipher.init(Cipher.DECRYPT_MODE, key,paramSpec);
		plainMsg = cipher.doFinal(hexToBytes(ciperText.trim()));
		}catch(Exception e){
			e.printStackTrace();
		}
		return new String(plainMsg).trim();
	}
	private static byte[] hexToBytes(String str) {
		if(str == null) return null;
		if(str.length() < 2)return null;
		int len = str.length() / 2;
		byte[] buffer = new byte[len];
		for(int i =0;i<len;i++){
			buffer[i]=(byte)Integer.parseInt(str.substring(i*2,i*2+2),16);
		}
		return buffer;
	}
}
