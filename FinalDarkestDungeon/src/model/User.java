package model;

public class User {
		private int userID;
		private int level;
		private String username;
		private int gold;
		private String team;
		
		public User(int userID, int level, String username, int gold, String team) {
				this.userID=userID;
				this.level=level;
				this.username=username;
				this.gold=gold;
				this.team=team;
		}
		
		public String toString() {
				return "<<<"+username+">>> [lvl:"+level+"] [gold:"+gold+"]";
		}
		
		public int getID() {
				return userID;
		}

		public String getTeam() {
				return team;
		}
		
		public int getLevel() {
				return level;
		}
		
		public int getGold() {
				return gold;
		}
		
		public void setLevel(int level) {
				this.level=level;
		}
		
		public void setGold(int gold) {
				this.gold=gold;
		}
}
