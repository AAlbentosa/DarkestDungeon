package model;

import java.util.Random;

public class Bandit extends model.Character{
	
		public Bandit() {
				super(5, 4, "Bandit", 4 ,2);
		}

		@Override
		public Character copy() {
				return new Bandit();
		}
	
		public int skill1() {	
				this.setLife(this.getLife()+2);
				return 2;		
		}
	
	
		public int skill2() {
				return -1;	
		}
	
		public int skill3() {
				Random r = new Random();
				if((r.nextInt(6)+1)>=4)
						return 3;
				else
						return 0;	
		}
	
		public String skill1Descripton(){
				return "Recover 2 life points and deal 1 point of damage";
		}
	
		public String skill2Descripton(){
				return "Reduce stress by 1";
		}
	
		public String skill3Descripton(){
				return "Rolls one 6-sided dice. If the result is 4, 5 or 6 causes 3 points of damage.";
		}
}
