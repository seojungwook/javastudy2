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
		System.out.println("해쉬값을 구할 문자열입력");
		plain = br.readLine().getBytes();
		System.out.println("평문:" + new String(plain));
		for (String al : algo) {
			//MessageDigest: 해쉬 알고리즘 기능을 가진 클래스
			MessageDigest md = MessageDigest.getInstance(al);
			hash = md.digest(plain);
			System.out.println(al + "의 해쉬값 크기:" + (hash.length * 8) + "bits");
			System.out.println("암호문");
			for (byte b : hash)
			System.out.printf("%02x", b);
			//02x:hex값 2자리 수로 출력 만약 앞에 숫자가 없으면 0으로 체워
			System.out.println();
		}
	}

}
