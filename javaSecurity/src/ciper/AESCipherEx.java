package ciper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;

/*
 * 키생성: key.ser
 * plain1.txt 평문 파일의 내용을 
 * 
 */
public class AESCipherEx {
	static Cipher cipher;
	public static void main(String[] args) {
		getKey();
		encryptFile("plain1.txt","cipher.txt");
		decryptFile("cipher.txt","plain2.txt");

	}

	
	private static void decryptFile(String cipherfile, String plainfile) {
		try{
			ObjectInputStream ois = new ObjectInputStream
					(new FileInputStream("Key.ser"));
			Key key = (Key)ois.readObject();
			ois.close();
			byte[] iv = new byte[]{
					(byte)0x8E,0x12,0x39,(byte)0x9c,
					 0x07,     0x72,0x6F,(byte)0x5A,
					(byte)0x8E,0x12,0x39,(byte)0x9C,
					 0x07,     0x72,0x6F,(byte)0x5A
			};
			AlgorithmParameterSpec paramSpec =
					new IvParameterSpec(iv);
			cipher.init(Cipher.DECRYPT_MODE,key,paramSpec);
			FileInputStream fis = new FileInputStream(cipherfile);
			FileOutputStream fos = new FileOutputStream(plainfile);
			CipherOutputStream cos = new CipherOutputStream(fos, cipher);
			byte[] buf = new byte[1024];
			int len;
			while ((len = fis.read(buf)) != -1) {
				cos.write(buf, 0, len);
			}
			fis.close();
			cos.flush();
			cos.close();
			fos.flush();
			fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


	private static void encryptFile(String plainfile, String cipherfile) {
		try{
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			ObjectInputStream ois = new ObjectInputStream
					(new FileInputStream("Key.ser"));
			Key key = (Key)ois.readObject();
			ois.close();
			byte[] iv = new byte[]{
					(byte)0x8E,0x12,0x39,(byte)0x9c,
					 0x07,     0x72,0x6F,(byte)0x5A,
					(byte)0x8E,0x12,0x39,(byte)0x9C,
					 0x07,     0x72,0x6F,(byte)0x5A
			};
			AlgorithmParameterSpec paramSpec =
					new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE,key,paramSpec);
			FileInputStream fis = new FileInputStream(plainfile);
			FileOutputStream fos = new FileOutputStream(cipherfile);
			CipherOutputStream cos = new CipherOutputStream(fos, cipher);
			byte[] buf = new byte[1024];
			int len;
			while ((len = fis.read(buf)) != -1) {
				cos.write(buf, 0, len);
			}
			fis.close();
			cos.flush();
			cos.close();
			fos.flush();
			fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	private static void getKey() {
		
		try {
			KeyGenerator Gkey = KeyGenerator.getInstance("AES");
			Gkey.init(128);
			Key key = Gkey.generateKey();
			ObjectOutputStream out = new ObjectOutputStream
					(new FileOutputStream("Key.ser"));
			out.writeObject(key);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
