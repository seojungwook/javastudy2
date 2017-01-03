package security;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;

public class DigestTest {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		byte[] plain = null;
		byte[] hash = null;
		
		String[] algo = { "MD5", "SHA-1", "SHA-256" };
		System.out.println("�ؽ����� ���� ���ڿ��Է�");
		plain = br.readLine().getBytes();
		System.out.println("��:" + new String(plain));
		for (String al : algo) {
			//MessageDigest: �ؽ� �˰��� ����� ���� Ŭ����
			MessageDigest md = MessageDigest.getInstance(al);
			hash = md.digest(plain);
			System.out.println(al + "�� �ؽ��� ũ��:" + (hash.length * 8) + "bits");
			System.out.println("��ȣ��");
			for (byte b : hash)
			System.out.printf("%02x", b);
			//02x:hex�� 2�ڸ� ���� ��� ���� �տ� ���ڰ� ������ 0���� ü��
			System.out.println();
		}
	}

}
