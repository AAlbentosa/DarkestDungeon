package model;

import java.util.Random;

public class BountyHunter extends model.Character{

		public BountyHunter() {
				super(3, 3, "BountyHunter", 3, 2);
		}
	
		@Override
		public Character copy() {
				return new BountyHunter();
		}

		public int skill1() {	
				this.setLife(this.getLife()+1);	
				if(this.getStress()>0)
						this.setStress(this.getStress()+1);	
				System.out.println(this.getType() + " recovers 1 point of live and reduce 1 stress point");
				return 0;
		}
	
	
		public int skill2() {
				Random r = new Random();
				if(((r.nextInt(6)+1)+(r.nextInt(6)+1))==7)
						return -5;
				else
						return 0;
		}
	
		public int skill3() {
				return -1;		
		}
	
		public String skill1Descripton(){
				return "Recovers 1 from stress and 1 from damage.";
		}
	
		public String skill2Descripton(){
				return "Rolls 2 6-sided dice. If the result of the sum is 7, reduce stress by 5 points";
		}
	
		public String skill3Descripton(){
				return "Reduces stress by 1";
		}
}
