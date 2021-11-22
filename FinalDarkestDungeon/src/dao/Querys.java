package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;

import at.favre.lib.crypto.bcrypt.BCrypt;
import model.User;

public class Querys {
	
		private Connection conn;
	
		public Querys(Connection connection) {
				conn=connection;
		}
	
		public User signIn(String username, String password) {
				int userID, gold, level;
				String team;
				String query = "select * from users where username = ?";
				
				if(conn==null)return null;
				try {
						PreparedStatement sentence= conn.prepareStatement(query);
						sentence.setString(1, username);
						ResultSet result = sentence.executeQuery();
						
						if(result.next()) {
								BCrypt.Result passwordcheck = BCrypt.verifyer().verify(password.toCharArray(), result.getString("password"));	              
								if(passwordcheck.verified) {
										userID=Integer.valueOf(result.getString("ID"));
										level=Integer.valueOf(result.getString("Level"));
										gold=Integer.valueOf(result.getString("Gold"));
										team=result.getString("Team");
										return new User(userID, level, result.getString("username"), gold, team);
								}else {
										return null;
								}
						}else {
								return null;
						}
				} catch (SQLException e) {
						System.out.println("Can't stablish connection to the database, check your internet connection!");
						return null;
				}
		}
	
	
		public User signUp(String username, String password) {
			
				String checkUsernameQuery = "select * from users where username = ? LIMIT 1";
				String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
			
				try {
						PreparedStatement sentence= conn.prepareStatement(checkUsernameQuery);
						sentence.setString(1, username);
						ResultSet result = sentence.executeQuery();
					
						if(result.next()) 
								System.out.println("username already registered");						
						else {
							
								String query = "INSERT INTO users(username, password) VALUES (?, ?)";
								try {
										sentence= conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
										sentence.setString(1, username);
										sentence.setString(2, bcryptHashString);
										sentence.executeUpdate();
								
										ResultSet user_id = sentence.getGeneratedKeys();  
										int key = user_id.next() ? user_id.getInt(1) : 0;
									
										if(key!=0)
												return new User(key, 1, username, 0, null);
										else {
												System.out.println("User cannot be registered, try again later.");
												return null;
										}
							
								} catch (SQLException e) {
										e.printStackTrace();
								}
						}
				} catch (SQLException e) {
						e.printStackTrace();
				}
				return null;	
		}
	
		public void saveGame(JSONArray team, User user) {
				PreparedStatement sentence;
				String query = "UPDATE users SET Level=?, Gold=?, Team=? WHERE ID=? LIMIT 1";
				try {
						sentence= conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
						sentence.setInt(1, user.getLevel());
						sentence.setInt(2, user.getGold());
						sentence.setString(3, team.toString());
						sentence.setInt(4, user.getID());
						sentence.executeUpdate();
			
						System.out.println("Your team have been saved correctly!");		
				} catch (SQLException e) {
						e.printStackTrace();
				}
		}

		public void showRanking() {
				int x=1;
				String query = "SELECT * FROM users ORDER BY Level DESC LIMIT 100";

				try {
						PreparedStatement sentence= conn.prepareStatement(query);
						ResultSet result = sentence.executeQuery();
					
						while(result.next()) {
								System.out.println(x+".-"+result.getString("username")+" [lvl:"+result.getString("Level")+"]");
								x++;
						}
				} catch (SQLException e) {
						e.printStackTrace();
				}
		}
}
