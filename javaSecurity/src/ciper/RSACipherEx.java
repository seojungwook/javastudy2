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
 * RSA��� ��ȣȭ ���α׷�
 * ����Ű�� ����Ű ���� => ���Ͽ� ����
 * �� , ��ȣ�� ,��� ���Ͽ� ����
 */
public class RSACipherEx {
	static Cipher cipher;

	public static void main(String[] args) {
		getKey();// Ű����
		encryptFile("p1.txt", "c.txt");// p1.txt �� c.txt ��ȣ��
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
			//cipher ��ü�� ��尡 ��ȣ���
			//fos���Ͽ� ��ȣȭ�� ������ ����
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
			key.initialize(2048);// Űũ��(2048��Ʈ)-������,
			KeyPair keypair = key.generateKeyPair();// Ű ��� ����
			PrivateKey priKey = keypair.getPrivate();// ����Ű
			PublicKey pubKey = keypair.getPublic();// ����Ű
			// Ű�� ���Ϸ� ����� .-��ü ��ü�� ���Ϸ� ����
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
