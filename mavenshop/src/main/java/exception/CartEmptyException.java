package exception;
//�ڹٿ��� ����ó���� �ʼ��� . ��RuntimeException ���ܴ� ���� �����ϴ� .
public class CartEmptyException extends RuntimeException {

	public CartEmptyException(String msg) {
		super(msg);
	}

}
