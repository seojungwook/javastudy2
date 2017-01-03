package chap1;

import java.util.List;

public class Project {
	private List<String> srcDirs;
	private String binDir;
	private Runner buildRunner;

	public void build() {
		buildRunner.build(srcDirs, binDir);

	}

	// seter�� ���� ��ü�� �����ϴ� ���
	public void setSrcDirs(List<String> srcDirs) {
		this.srcDirs = srcDirs;
	}

	public void setBinDir(String binDir) {
		this.binDir = binDir;//"bin"
	}
    //setBuildRunner : chap1.BuildRunner(), path="c:\"
	public void setBuildRunner(Runner buildRunner) {
		this.buildRunner = buildRunner;
	}

}
