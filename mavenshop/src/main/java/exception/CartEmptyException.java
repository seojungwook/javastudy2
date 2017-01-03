package exception;
//자바에서 예외처리는 필수임 . 단RuntimeException 예외는 생략 가능하다 .
public class CartEmptyException extends RuntimeException {

	public CartEmptyException(String msg) {
		super(msg);
	}

}
