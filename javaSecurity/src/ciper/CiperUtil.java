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
		//AES�� �˰������� Ű ũ�� :128��Ʈ ~192��Ʈ 
		KeyGenerator keyGen = KeyGenerator.getInstance(algo);
		//Ű�� ũ�� ����
		//keyGen.init(128);//128��Ʈ ũ���� Ű ���� 
		SecretKey key = keyGen.generateKey();
		return key.getEncoded();
	}
	//��ȣ���� �������ִ� �޼���
	public static String encrypt(String msg) {
		Cipher cipher;
		byte[] ciperMsg = new byte[1024];
		try{
			/*
			 * AES : ��ȣ �˰���
			 *       �� �˰����� ����(64��Ʈ�� ����� �˰����� ��� )
			 * CBC(CipherBlock Chaining):�պ��� ��ȣ���� �ں��� 
			 *                           ��ȣȭŰ�� �߰� �Ǵ� �׷� �˰��� ���
			 * PKCS5Padding: 64��Ʈ ������ ��ȣȭ�Ͻ� ������ ���� �е� �� ���ؼ� 64��Ʈ�� ä���ش� .                 
			 */      
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			//��ĪŰ �ڵ�����
			randomKey = genRandomKey("AES");
			Key key = new SecretKeySpec(randomKey, "AES");
			//�ʱ�ȭ ����(initial vector):ü�̴׿� ����ϴ� 
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
