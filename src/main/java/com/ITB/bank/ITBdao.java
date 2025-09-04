package com.ITB.bank;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ITBdao {
	final String url = System.getenv("DB_URL");
	final String user = System.getenv("DB_USER");
	final String pass = System.getenv("DB_PASS");
	Connection con;
	public  ITBdao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pass);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public Account addUser(String name, String dob, String gender, String martial, String account_type,
            String state, String city, int pincode, double amount, String username, String password) {
		try {
			String insertAccount = "INSERT INTO ACCOUNT VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			String insertTxn = "INSERT INTO TRANSACTION_HISTORY(username, amount_before, current_balance, amount, transaction_message, txn_date) VALUES(?,?,?,?,?,NOW())";

			PreparedStatement p = con.prepareStatement(insertAccount);
			p.setString(1, name);
			p.setString(2, dob);
			p.setString(3, gender);
			p.setString(4, martial);
			p.setString(5, account_type);
			p.setString(6, state);
			p.setString(7, city);
			p.setInt(8, pincode);
			p.setDouble(9, amount);
			p.setString(10, username);
			p.setString(11, password);
			p.execute();
			
			// --- 2. Insert initial deposit into Transaction_History ---
			PreparedStatement psTxn = con.prepareStatement(insertTxn);
			psTxn.setString(1, username);
			psTxn.setDouble(2, 0.0);              // old balance (before account open = 0)
			psTxn.setDouble(3, amount);           // current balance
			psTxn.setDouble(4, amount);           // initial deposit amount
			psTxn.setString(5, "Account Credited with Initial Deposit");
			psTxn.execute();
			
			return new Account(name, dob, gender, martial, account_type, state, city, pincode, amount, username, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			} 
	}
	

	public Account getUser(String username,String password)  {
		try {
		String query="SELECT * FROM ACCOUNT WHERE USERNAME=? AND PASSWORD=?";
		PreparedStatement  p=con.prepareStatement(query);
		p.setString(1, username);
		p.setString(2,password);
		ResultSet rs=p.executeQuery();
		if(rs.next()) {
			Account a=new Account(rs.getString(1),rs.getString(2),rs.getString(3),
					rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
					rs.getInt(8),rs.getDouble(9),rs.getString(10),rs.getString(11));
			return a;
		}else return null;
		}catch(Exception e) {
			return null;
		}
	}
	
	public Account get_data(String username){
		try {
			String query="SELECT * FROM ACCOUNT WHERE USERNAME=?";
			PreparedStatement  p=con.prepareStatement(query);
			p.setString(1, username);
			ResultSet rs=p.executeQuery();
			if(rs.next()) {
			Account a=new Account(rs.getString(1),rs.getString(2),rs.getString(3),
					rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
					rs.getInt(8),rs.getDouble(9),rs.getString(10),rs.getString(11));
			return a;
			}else return null;
		}catch(Exception e) {
			return null;
		}
	}
	
	public boolean update_balance(Account sender, Account receiver, double amount) {
	    String updateQuery = "UPDATE ACCOUNT SET balance=? WHERE username=?";
	    String insertTxn = "INSERT INTO TRANSACTION_HISTORY(username, amount_before, current_balance, amount, transaction_message, txn_date) VALUES(?,?,?,?,?,NOW())";


	    try {
	        con.setAutoCommit(false); // Begin transaction

	        // --- 1. Deduct from Sender ---
	        double senderOldBalance = sender.getAmount();
	        double senderNewBalance = senderOldBalance - amount;

	        try (PreparedStatement ps1 = con.prepareStatement(updateQuery)) {
	            ps1.setDouble(1, senderNewBalance);
	            ps1.setString(2, sender.getUsername());
	            ps1.executeUpdate();
	        }

	        try (PreparedStatement psTxn1 = con.prepareStatement(insertTxn)) {
	            psTxn1.setString(1, sender.getUsername());
	            psTxn1.setDouble(2, senderOldBalance);
	            psTxn1.setDouble(3, senderNewBalance);
	            psTxn1.setDouble(4, amount);
	            psTxn1.setString(5, "Transferred " + amount + " to " + receiver.getUsername());
	            psTxn1.executeUpdate();
	        }

	        // --- 2. Add to Receiver ---
	        double receiverOldBalance = receiver.getAmount();
	        double receiverNewBalance = receiverOldBalance + amount;

	        try (PreparedStatement ps2 = con.prepareStatement(updateQuery)) {
	            ps2.setDouble(1, receiverNewBalance);
	            ps2.setString(2, receiver.getUsername());
	            ps2.executeUpdate();
	        }

	        try (PreparedStatement psTxn2 = con.prepareStatement(insertTxn)) {
	            psTxn2.setString(1, receiver.getUsername());
	            psTxn2.setDouble(2, receiverOldBalance);
	            psTxn2.setDouble(3, receiverNewBalance);
	            psTxn2.setDouble(4, amount);
	            psTxn2.setString(5, "Received " + amount + " from " + sender.getUsername());
	            psTxn2.executeUpdate();
	        }

	        // ✅ Commit only if ALL updates succeed
	        con.commit();
	        return true;

	    } catch (Exception e) {
	        try {
	            con.rollback(); // ❌ Rollback on failure
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            con.setAutoCommit(true); // Restore auto-commit
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}

	public boolean deposit_money(Account user,double amount){
	    try {
	        double oldBalance = user.getAmount();
	        double newBalance = oldBalance + amount;

	        String query="UPDATE ACCOUNT SET balance=? WHERE username=?";
	        PreparedStatement ps=con.prepareStatement(query);
	        ps.setDouble(1, newBalance);
	        ps.setString(2, user.getUsername());
	        ps.executeUpdate();

	        String historyQuery="INSERT INTO TRANSACTION_HISTORY (username, amount_before, current_balance, amount, transaction_message) VALUES (?,?,?,?,?)";

	        PreparedStatement h=con.prepareStatement(historyQuery);
	        h.setString(1, user.getUsername());
	        h.setDouble(2, oldBalance);
	        h.setDouble(3, newBalance);
	        h.setDouble(4, amount);
	        h.setString(5, "AMOUNT CREDITED");
	        h.executeUpdate();

	        return true;
	    }catch(Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	
	public boolean withdraw_money(Account user, double amount) {
	    try {
	        double oldBalance = user.getAmount();
	        double newBalance = oldBalance - amount;

	        // 1. Update balance in Account
	        String query = "UPDATE ACCOUNT SET balance=? WHERE username=?";
	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setDouble(1, newBalance);
	        ps.setString(2, user.getUsername());
	        ps.executeUpdate();

	        // 2. Insert into Transaction_History
	        String historyQuery="INSERT INTO TRANSACTION_HISTORY (username, amount_before, current_balance, amount, transaction_message) VALUES (?,?,?,?,?)";

	        PreparedStatement h = con.prepareStatement(historyQuery);
	        h.setString(1, user.getUsername());
	        h.setDouble(2, oldBalance);
	        h.setDouble(3, newBalance);
	        h.setDouble(4, amount);
	        h.setString(5, "AMOUNT DEBITED");
	        h.executeUpdate();

	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	
	public List<Transaction> getTransaction_history(String name) {
		String query="Select * from TRANSACTION_HISTORY where username=? order by Txn_date DESC";
		String username=name;
		List<Transaction> list=new ArrayList<Transaction>();
		try {
			PreparedStatement p=con.prepareStatement(query);
			p.setString(1, username);
			ResultSet rs=p.executeQuery();
			while(rs.next()) {
				String un=rs.getString(1);
				String Transaction_Message =rs.getString(5);
				String Transaction_Date_Time = rs.getString(6) ;
				double Amount = rs.getDouble(4);
				double Old_Balance = rs.getDouble(2);
				double Current_Balance = rs.getDouble(3);
				
				Transaction t=new Transaction(Transaction_Message,Transaction_Date_Time, Amount,Old_Balance,
						 Current_Balance);
				list.add(t);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
}
