package chap1;

import java.util.List;

public class BuildRunner implements Runner{
	private String path;
	@Override
	public void build(List<String> srcDirs, String binDir){
		String info ="프로젝트 경로 :"+ path +"\n";
		for(String dir : srcDirs){
			info+="소스경로:"+ dir +"\n";
		}
		info += "클래스 파일경로:"+binDir + "\n";
		System.out.println(info);
		System.out.println("BuildRunner.build() 실행");
		
	}
	public void setPath(String path) {
		this.path = path;
	}
	
		
	}


