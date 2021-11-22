package model;

import java.util.ArrayList;
import java.util.Random;

import userinputs.Numbers;

public class Character{
		private int life;
		private int stress;
		private boolean alive;
		private String type;
		private int maxscope;
		private int minscope;
		private int level;
		private int maxlife;
		private int maxstress;
		private Numbers input;
	
		public Character(int maxlife, int maxstress, String type, int maxscope, int minscope) {
				this.life=maxlife;
				this.maxlife=maxlife;
				this.stress=0;
				this.maxstress=maxstress;
				this.alive=true;
				this.type=type;
				this.maxscope=maxscope;
				this.minscope=minscope;
				this.level=1;
				input=new Numbers();
		}
	
		public void damage(int dmg) {
				if(dmg>0)
						this.life=this.life-dmg;
				else
						this.stress=this.stress+(dmg*-1);
				this.checkAlive();
		}
	
		public int menu() {
				System.out.println("\nTurn of: ["+this.type+", life="+this.life+", stress="+this.stress+"]");
				System.out.println("Select one hability to attack:");
				System.out.println("1.-"+skill1Descripton()+"\n2.-"+skill2Descripton()+"\n3.-"+skill3Descripton());
				switch(input.selectOption(1, 3)) {
						case 1:
								return skill1();
						case 2:
								return skill2();
						case 3:
								return skill3();
						default:
								return 0;
				}
		}
	
		public int getRealMaxScope(ArrayList<Character> enemies) {
				if (enemies.size() > this.getMaxScope()) 
						return this.getMaxScope();
				else 
						return enemies.size();
		}
	
		public int skill1() {		
				System.out.println("Basic skill");
				return 0;		
		}
	
		public int skill2() {
				System.out.println("Basic skill");
				return 0;		
		}

		public int skill3() {	
				System.out.println("Basic skill");
				return 0;		
		}
	
		public String skill1Descripton(){
				return "Has no effect";
		}
	
		public String skill2Descripton(){
				return "Has no effect";
		}
	
		public String skill3Descripton(){
				return "Has no effect";
		}
	
		public int randomskill() {
				Random r = new Random();
				switch(r.nextInt(3)) {
						case 0:
								return skill1();
						case 1:
								return skill2();
						case 2:
								return skill3();
				}
				return 0;
		}
	
		public boolean checkStress() {
				if(this.stress>0)
						return true;
				else
						return false;
		}

		public void checkAlive() {
				if(this.life<=0 || this.stress>this.maxstress)
						this.alive=false;		
		}

		public void sleep() {
				this.life=this.maxlife;
				this.stress=0;
		}
	
		public void setLife(int life) {
				this.life=life;
		}
	
		public void setStress(int stress) {
				this.stress=stress;
		}

		public String toString() {	
				return "[type="+type+" life:"+life+", stress:"+stress+"]";
		}

		public void setLevel(int level) {
				this.level=level;
				this.life=this.maxlife*level;
				this.stress=0;
				this.maxstress*=level;
		}
	
		public Character copy() {
				return new Character(this.life, this.stress, this.type, this.maxscope, this.minscope);
		}
		
		public boolean getAlive() {
				return alive;
		}

		public int getMinScope() {
				return minscope;
		}

		public int getMaxScope() {
				return maxscope;
		}

		public String getType() {
				return type;
		}

		public int getLevel() {
				return this.level;
		}

		public int getStress() {
				return this.stress;
		}

		public int getLife() {
				return this.life;
		}
}
