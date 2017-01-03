package ch6.first;

import javax.swing.plaf.synth.SynthSeparatorUI;

//private 배열 맴버가 public 맴버처럼 사용됨
public class ColorTest {

	public static void main(String[] args) {
		UColors ucolor = new UColors();
		for (String c : ucolor.getColors())
			System.out.println(c);
		System.out.println("\n yellow로 값변경");
		String[] uc = ucolor.getColors();
		uc[0] = "yellow";
		for (String c : ucolor.getColors())
			System.out.println(c);
		
		System.out.println("안전한 코드로 실행");
		SColors scolor = new SColors();
		for (String s : scolor.getColors())
			System.out.println(s);
		System.out.println("\n yellow로 값변경");
		String[] sc = scolor.getColors();
		sc[0] = "yellow";
		for(String s : scolor.getColors()){
			System.out.println(s);
		}
		
	}
}

	class UColors {
		private String[] colors = { "red", "blue", "white" };
		public String[] getColors() {
			return colors;
		}

	}
	class SColors{
		private String[] colors = { "red", "blue", "white" };
		public String[] getColors() {
			String[] ret = null;
			if(this.colors !=null){
				ret = new String[colors.length];
				for(int i=0;i<colors.length;i++){
					ret[i] = colors[i];
				}
			}
			return ret;
		}
	}


