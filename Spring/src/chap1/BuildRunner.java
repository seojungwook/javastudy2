package chap1;

import java.util.List;

public class BuildRunner implements Runner{
	private String path;
	@Override
	public void build(List<String> srcDirs, String binDir){
		String info ="������Ʈ ��� :"+ path +"\n";
		for(String dir : srcDirs){
			info+="�ҽ����:"+ dir +"\n";
		}
		info += "Ŭ���� ���ϰ��:"+binDir + "\n";
		System.out.println(info);
		System.out.println("BuildRunner.build() ����");
		
	}
	public void setPath(String path) {
		this.path = path;
	}
	
		
	}


