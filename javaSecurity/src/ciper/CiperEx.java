package ciper;

public class CiperEx {
	public static void main(String[] args) {
		String plainText = "�ȳ��ϼ���. ȫ�浿 �Դϴ� .";
        String ciperText = CiperUtil.encrypt(plainText);//��ȣȭ
        System.out.println("��ȣ��:"+ciperText);
        String plainText2 =CiperUtil.decrypt(ciperText);//��ȣȭ
        System.out.println("��:"+plainText2);
	}

}
