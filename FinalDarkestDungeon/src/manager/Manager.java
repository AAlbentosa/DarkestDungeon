package manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import dao.MysqlCon;
import dao.Querys;

import graphics.MainFrame;
import graphics.panels.SignIn;
import graphics.panels.SignUp;

import model.Abomination;
import model.Bandit;
import model.BountyHunter;
import model.Character;
import model.GraveRaider;
import model.User;

import userinputs.Numbers;

import org.json.JSONArray;

public class Manager {
		private MainFrame frame;
		private SignIn signin;
		private SignUp signup;
		private MysqlCon conn;
		private Querys querys;
		private User user;
		private Random rand;
		private boolean exit;
		private ArrayList<model.Character> characters;
		private ArrayList<model.Character> enemies;
		private Character[] characterslist;
		private Numbers input;
	
		public Manager () {
				frame = new MainFrame();
				conn=new MysqlCon();
				querys=new Querys(conn.getConnection());
				characters = new ArrayList<model.Character>();
				characterslist = new Character[] { new Abomination(), new BountyHunter(), new GraveRaider(), new Bandit() };
				rand=new Random();
				input=new Numbers();
				exit=false;
		}
	    
		public void init () {
				frame.setVisible(true);
				signIn();	
		}
		
//User authentication		
		private void signIn() {
				signin=new SignIn();
				frame.getContentPane().removeAll();
				frame.setContentPane(signin);
				frame.repaint();	
        
				signin.getSignUp().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
								signUp();
						}
				});
        
				signin.getCloseButton().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
								frame.dispose();
						}
				});
        
				signin.getSignInButton().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
								trySignIn();
						}
				});
		}
	
		private void trySignIn() {
				user=querys.signIn(signin.getUsername(), signin.getPassword());
				if(user!=null){
						frame.setVisible(false);
						startGame();
				}else {
						System.out.println("Wrong username/password combination");
						signIn();
				}
		}
	
		private void signUp() {
				signup = new SignUp();
				frame.getContentPane().removeAll();
				frame.setContentPane(signup);
				frame.repaint();
        
				signup.getSignInButton().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
								signIn();
						}
				});
        
				signup.getCloseButton().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
								frame.dispose();
						}
				});

				signup.getSignUpButton().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
								trySignUp();
						}
				});
		}
	
		private void trySignUp() {
				user=querys.signUp(signup.getUsername(), signup.getPassword());
				if(user!=null){
						frame.setVisible(false);
						startGame();
				}else {
						System.out.println("User haven't been registered, try again");
						signUp();
				}
		}
	    
//Start game
		public void startGame() {
				System.out.println("Welcome "+user.toString());
				if(user.getTeam()==null) {
						System.out.println("Select 4 characters:");
						selectCharacters();
				}else {
						jsonTeamDecode(user.getTeam());
				}
				System.out.println("--> STARTING GAME <--");
				menu();
		}
		
		

		private void menu() {
				do {
						showCharacters(characters);
						System.out.println("\n1.-Explore Dungeon\n2.-Sleep\n3.-Reorder team\n4.-Save game\n5.-World Ranking\n0.-Finish game");
						switch (input.selectOption(0, 5)) {
								case 1:
										newDungeon();
										break;
								case 2:
										sleep();
										break;
								case 3:
										rearrangeTeam();
										break;
										
								case 4:
										saveGame();
										break;
										
								case 5:	
										System.out.println("World players ranking:");
										querys.showRanking();
										System.out.println();
										break;
								
								case 0:
										exit=true;
										break;
						}						
				}while (!exit);
				System.exit(0);
		}

//Dungeon
		private void newDungeon() {
				generateEnemies();
				do {
						charactersAttack();
						enemiesAttack();
				} while (charactersAlive(characters) && charactersAlive(enemies));
				
				if(charactersAlive(characters)) {
						System.out.println("Congratulations! your team won!");
						System.out.println("-Increase level +1\n-Receive 1000 of gold.");
						user.setLevel(user.getLevel()+1);
						user.setGold(user.getGold()+1000);
				}else {
						System.out.println("Your characters are dead, good luck the next time");
				}
				resetTeam();
		}
		
		private void enemiesAttack() {
				Character character;
				for (Character enemy : enemies) {
						if(charactersAlive(characters)) {
								do {
										character = characters.get(getRandom(0, 4));
								}while(!character.getAlive());
								character.damage(enemy.randomskill());
						}
				}
		}

		private void charactersAttack() {
				Character enemytoattack;
				int  skill, maxscope, minscope;

				for (Character character : characters) {
						if (character.getAlive() && character.getMinScope() <= enemies.size()) {
								maxscope=character.getRealMaxScope(enemies) - 1;
								minscope=character.getMinScope()-1;
								skill=character.menu();
								showEnemiesForAttack(enemies, minscope, maxscope);
								enemytoattack = enemies.get((input.selectOption(minscope+1, maxscope+1)-1));
								enemytoattack.damage(skill);
								if (!enemytoattack.getAlive())
										enemies.remove(enemytoattack);
						}
				}
		}
		
		private boolean charactersAlive(ArrayList<model.Character> characters) {
				for (model.Character character : characters) {
						if (character.getAlive())
								return true;
				}
				return false;
		}
		
		private void resetTeam() {
				for (int x = 0; x < characters.size(); x++) {
						if (!characters.get(x).getAlive()) {
								characters.remove(x);
								x--;
						} else {
								characters.get(x).setLevel(characters.get(x).getLevel()+1);
						}
				}
				System.out.println("Dead characters has been removed and hurt characters recovered.");
				selectCharacters();
		}

//sleep
		private void sleep() {
				characters.forEach((character) -> character.sleep());
				System.out.println("All characters have recovered their life and stress points");
		}

//rearrange team
		private void rearrangeTeam() {
				int fromposition;
				
				showCharacters(characters);
				System.out.println("Which character do you want to move?");
				fromposition = input.selectOption(1, 4);
				System.out.println("To what position?");
				charactersSwap(fromposition-1, input.selectOption(1, 4)-1);
		}
		
		private void charactersSwap(int from, int to) {
				Character aux = characters.get(from);
				characters.set(from, characters.get(to));
				characters.set(to, aux);
		}

//save game
		private void saveGame() {
			querys.saveGame(jsonTeamEncode(), user);
		}
		
	    private JSONArray jsonTeamEncode() {	    	
        		String[][] charactersToArray = new String[4][2];
        	
        		for(int x=0; x<charactersToArray.length; x++) {
        				charactersToArray[x][0]=characters.get(x).getType();
        				charactersToArray[x][1]=""+characters.get(x).getLevel();
        		}
        		JSONArray team = new JSONArray(charactersToArray);
        		return team;
	    }
	    
//Other
		private void generateEnemies() {
				enemies = new ArrayList<model.Character>();
				Character enemy;
				int level = getMedianLevel();
				
				for (int x = 0; x < (getRandom(0, 4) + 1); x++) {
						enemy=characterslist[getRandom(0, 4)].copy();
						enemy.setLevel(level);
						enemies.add(enemy);
				}
				System.out.println("Enemies created:");
				showCharacters(enemies);
		}

		

		private void showEnemiesForAttack(ArrayList<Character> enemies, int minrange, int maxrange) {
				System.out.println("Select an anemy:");
				for (int x = minrange; x <= maxrange; x++) 
						System.out.println((x + 1) + ".-" + enemies.get(x).toString());
		}

		private void showCharacters(ArrayList<Character> characters) {
				characters.forEach((character) -> System.out.println("-" + character.toString()));
		}

		private int getRandom(int min, int max) {	
				return rand.nextInt(max) + min;
		}

		private void selectCharacters() {
				while (characters.size() < 4) {
						System.out.println("Character " + (characters.size() + 1));
						showCharactersList();
						characters.add(characterslist[input.selectOption(1, 4) - 1].copy());
				}
		}

		private void showCharactersList() {
				for (int x = 0; x < 4; x++) {
						System.out.println(x + 1 + ".-" + characterslist[x].toString());
				}
		}

		private int getMedianLevel() {
				int sum = 0;
				for (model.Character character : characters)
						sum += character.getLevel();
				return sum / 4;
		}
		
		private void jsonTeamDecode(String Team) {
				characters = new ArrayList<Character>();
				JSONArray encodedTeam=new JSONArray(Team);
				JSONArray pointer;
		
				for(int x=0; x<encodedTeam.length(); x++) {
						pointer = encodedTeam.getJSONArray(x);
						switch(pointer.getString(0)) {
				
								case "Abomination":
										characters.add(new Abomination());
										break;
								
								case "Bandit":
										characters.add(new Bandit());
										break;
								
								case "GraveRaider":
										characters.add(new GraveRaider());
										break;
						
								case "BountyHunter":
										characters.add(new BountyHunter());
										break;
						}
						characters.get(x).setLevel(Integer.valueOf(pointer.getString(1)));
				}
		}
}
