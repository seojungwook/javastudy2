package ciper;

public class CiperEx {
	public static void main(String[] args) {
		String plainText = "안녕하세요. 홍길동 입니다 .";
        String ciperText = CiperUtil.encrypt(plainText);//암호화
        System.out.println("암호문:"+ciperText);
        String plainText2 =CiperUtil.decrypt(ciperText);//복호화
        System.out.println("평문:"+plainText2);
	}

}
