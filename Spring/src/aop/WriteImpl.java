package aop;

public class WriteImpl {

	private ArticleDao dao;
	public WriteImpl(ArticleDao dao) {
		this.dao=dao;
	}
	public void write() {
		System.out.println("WriteImpl.write()½ÇÇà");
		dao.insert();
	}
}
