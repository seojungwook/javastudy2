package chap1;

import java.io.FileWriter;
import java.io.IOException;

public class FileOutputter {
private String filePath;

public void setFilePath(String filePath) {
	this.filePath = filePath;
}

public void output(String message) throws IOException {
FileWriter out = new FileWriter(filePath);
out.write(message);
out.flush();
out.close();
}


}
