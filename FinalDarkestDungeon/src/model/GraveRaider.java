package model;

import java.util.Random;

public class GraveRaider extends model.Character{

		public GraveRaider() {
				super(4, 5, "GraveRaider", 3, 1);
		}
	
@Override
		public Character copy() {
				return new GraveRaider();
		}

		public int skill1() {	
				Random r = new Random();
				if((r.nextInt(6)+1)<=3)
						return 3;
				else
						return 0;
		}
	
		public int skill2() {
				return 2;
		}
	
		public int skill3() {
				Random r = new Random();
				if((r.nextInt(6)+1)>=5)
						return -2;
				else
						return 0;		
		}
	
		public String skill1Descripton(){
				return "Rolls one 6-sided dice. If the result is 1, 2 or 3 it causes damage 3.";
		}
	
		public String skill2Descripton(){
				return "Causes damage 2";
		}
	
		public String skill3Descripton(){
				return "Rolls one 6-sided dice. If the result is 5 or 6, reduce stress by 2";
		}
}
