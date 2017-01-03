package ciper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;

/*
 * RSA방식 암호화 프로그램
 * 개인키와 공개키 생성 => 파일에 저장
 * 평문 , 암호문 ,모두 파일에 저장
 */
public class RSACipherEx {
	static Cipher cipher;

	public static void main(String[] args) {
		getKey();// 키생성
		encryptFile("p1.txt", "c.txt");// p1.txt 평문 c.txt 암호문
		decryptFile("c.txt", "p2.txt");

	}
	private static byte[] makeKey(String studkey) {
		int lenght = studkey.length();
		char ch = 'A';
		for (int i = lenght; i < 16; i++) {
			studkey += ch++;
		}
		return studkey.getBytes();

	}
	private static void decryptFile(String cipherfile, String plainfile) {
		try {
			ObjectInputStream ois = new ObjectInputStream
					(new FileInputStream("privateKey.ser"));
			PrivateKey pubKey = (PrivateKey) ois.readObject();
			ois.close();
			cipher.init(Cipher.DECRYPT_MODE, pubKey);
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
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void encryptFile(String plainfile, String cipherfile) {
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			ObjectInputStream ois = new ObjectInputStream
					(new FileInputStream("publicKey.ser"));
			PublicKey priKey = (PublicKey) ois.readObject();
			ois.close();
			cipher.init(Cipher.ENCRYPT_MODE, priKey);
			FileInputStream fis = new FileInputStream(plainfile);
			FileOutputStream fos = new FileOutputStream(cipherfile);
			//cipher 객체의 모드가 암호기능
			//fos파일에 암호화된 내용을 쓰기
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
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void getKey() {
		try {
			KeyPairGenerator key = KeyPairGenerator.getInstance("RSA");
			key.initialize(2048);// 키크기(2048비트)-안정적,
			KeyPair keypair = key.generateKeyPair();// 키 페어 생성
			PrivateKey priKey = keypair.getPrivate();// 개인키
			PublicKey pubKey = keypair.getPublic();// 공개키
			// 키를 파일로 만든다 .-객체 자체를 파일로 저장
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("privateKey.ser"));
			out.writeObject(priKey);
			out.flush();
			out.close();
			out = new ObjectOutputStream(new FileOutputStream("publicKey.ser"));
			out.writeObject(pubKey);
			out.flush();
			out.close();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
