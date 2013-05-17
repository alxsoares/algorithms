package alex.datastructures;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileReadingKLastLines {

	public static void printLastKLines(String file, int k) throws IOException{
		
		String [] lines = new String[k];
		if(k<=0) return;
		FileInputStream fis = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
		String line="";
		int lineNumber=0;
		while((line=reader.readLine())!= null){
			lines[lineNumber%k]=line;
			lineNumber++;
		}
		int start, count;
		if(lineNumber <k){
			start=0;
			count=lineNumber;
		}else{
			start = lineNumber%k;
			count = k;
		}
		for (int i = 0; i < count; i++) {
			System.out.println(lines[(i+start)%k]);
		}
		reader.close();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
