package test;


public class testtoy {

	public static void main(String[] args) {
		
		
		boolean [][] map =  make();
		//boolean [][] map = {{true,true,true,false},{false,false,false,false},{false,false,false,false},{false,false,false,false}};
		int [][] map2 = new int [map.length][map[0].length];
		
		
		for(int e = 0; e < map.length; e ++){
			for(int p = 0; p<map[0].length; p++){
				System.out.print(map[e][p]+"|");
			}
			System.out.println("");
		}

		map2 =  doit(map);

		for(int e = 0; e < map2.length; e ++){
			for(int p = 0; p<map2[0].length; p++){
				System.out.print(map2[e][p]+"|");
			}
			System.out.println("");
		}
		
	}
	
	 
	//주위 8칸 주변에 폭탄있을시 +1 카운트  
	public static int[][] doit(boolean[][] map){
		
		 int [][] map3 = new int [map.length][map[0].length];
		
		for (int t = 0 ; t < map.length ; t++ ){
			for(int j = 0 ; j <  map[0].length; j++){
				for(int i = t-1; i<= t+1; i++){
					for(int v =j-1 ; v <=j+1; v++){
						if(t==i && j==v){
							continue;
						}else{
							try{
								if(map[t][j])map3[i][v] +=1;
							}catch(Exception e){
								
							}
							
						}
					}
				}
				
			}
				
		}
		return map3;	
	}
	
	public static boolean[][] make() {
		
		int ranx = (int)(Math.random() *10)+20 ;
		int rany = (int)(Math.random() *10)+20 ;
		int minecnt = 100 ;
		int cnt =0;
		
		boolean [][] map =new boolean [ranx][rany]; 
		
		for(int i = 0 ; i < map.length; i++){
			int rcnt = 0;
			for(int j = 0; j< map.length; j++){
				if(cnt < minecnt ){
					if(rcnt < 6){
						int rate = (int)(Math.random()*4)+1;
						if(rate == 3){
							map[i][j]=true;
							rcnt++;
							cnt++;
						}else{
							map[i][j]=false;
						}
					}else{
						map[i][j]=false;
					}
				}else{
					map[i][j]=false;
				}
			}
			
		}
		
		return map;
	}
	
	
}


