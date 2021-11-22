package model;

import java.util.Random;

public class Abomination extends model.Character{
	
		public Abomination(){
				super(3, 4, "Abomination", 2, 1);
		}

		@Override
		public Character copy() {
				return new Abomination();
		}
	
		public int skill1() {
				System.out.println(this.getType() + " Inflicts 3 points of damage");
				return 3;		
		}
	
	
		public int skill2() {
				Random r = new Random();
				int result = (r.nextInt(6)+1)+(r.nextInt(6)+1);
				System.out.println(this.getType() + " Inflicts "+ (result) +"points of damage");
				return result;		
		}
	
		public int skill3() {
				System.out.println(this.getType() + " increments enemy stress by 1.");
				return -1;		
		}
	
		public String skill1Descripton(){
				return "Deals 3 points of damage";
		}
	
		public String skill2Descripton(){
				return "Roll two dice and deal the sum as damage";
		}
	
		public String skill3Descripton(){
				return "Reduces stress by 1";
		}
}