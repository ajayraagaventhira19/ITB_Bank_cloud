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
	
	public boolean update_balance(String senderUsername, String receiverUsername, double amount) {
    String getBalance = "SELECT balance FROM ACCOUNT WHERE username=?";
    String updateQuery = "UPDATE ACCOUNT SET balance=? WHERE username=?";
    String insertTxn = "INSERT INTO TRANSACTION_HISTORY(username, amount_before, current_balance, amount, transaction_message, txn_date) VALUES(?,?,?,?,?,NOW())";

    try {
        double senderOldBalance = 0;
        try (PreparedStatement ps = con.prepareStatement(getBalance)) {
            ps.setString(1, senderUsername);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) senderOldBalance = rs.getDouble("balance");
            else return false; 
        }

        if (senderOldBalance < amount) return false; 
        double senderNewBalance = senderOldBalance - amount;

        double receiverOldBalance = 0;
        try (PreparedStatement ps = con.prepareStatement(getBalance)) {
            ps.setString(1, receiverUsername);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) receiverOldBalance = rs.getDouble("balance");
            else return false; 
        }
        double receiverNewBalance = receiverOldBalance + amount;

        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setDouble(1, senderNewBalance);
            ps.setString(2, senderUsername);
            ps.executeUpdate();
        }
        try (PreparedStatement ps = con.prepareStatement(insertTxn)) {
            ps.setString(1, senderUsername);
            ps.setDouble(2, senderOldBalance);
            ps.setDouble(3, senderNewBalance);
            ps.setDouble(4, amount);
            ps.setString(5, "Transferred " + amount + " to " + receiverUsername);
            ps.executeUpdate();
        }

        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setDouble(1, receiverNewBalance);
            ps.setString(2, receiverUsername);
            ps.executeUpdate();
        }
        try (PreparedStatement ps = con.prepareStatement(insertTxn)) {
            ps.setString(1, receiverUsername);
            ps.setDouble(2, receiverOldBalance);
            ps.setDouble(3, receiverNewBalance);
            ps.setDouble(4, amount);
            ps.setString(5, "Received " + amount + " from " + senderUsername);
            ps.executeUpdate();
        }

        return true;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

	// Deposit money for a given account
public boolean deposit_money(Account user, double amount) {
    try {
        // 1. Get old balance from Account object
        double oldBalance = user.getAmount();
        double newBalance = oldBalance + amount;

        // 2. Update balance in ACCOUNT table
        String updateQuery = "UPDATE ACCOUNT SET balance=? WHERE username=?";
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setDouble(1, newBalance);
            ps.setString(2, user.getUsername());
            ps.executeUpdate();
        }

        // 3. Insert into TRANSACTION_HISTORY
        String historyQuery = "INSERT INTO TRANSACTION_HISTORY (username, amount_before, current_balance, amount, transaction_message, txn_date) VALUES (?, ?, ?, ?, ?, NOW())";
        try (PreparedStatement ps = con.prepareStatement(historyQuery)) {
            ps.setString(1, user.getUsername());
            ps.setDouble(2, oldBalance);
            ps.setDouble(3, newBalance);
            ps.setDouble(4, amount);
            ps.setString(5, "AMOUNT CREDITED");
            ps.executeUpdate();
        }

        return true;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

// Withdraw money for a given account
public boolean withdraw_money(Account user, double amount) {
    try {
        // 1. Get old balance from Account object
        double oldBalance = user.getAmount();

        // 2. Check if sufficient balance
        if (oldBalance < amount) return false;

        double newBalance = oldBalance - amount;

        // 3. Update balance in ACCOUNT table
        String updateQuery = "UPDATE ACCOUNT SET balance=? WHERE username=?";
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setDouble(1, newBalance);
            ps.setString(2, user.getUsername());
            ps.executeUpdate();
        }

        // 4. Insert into TRANSACTION_HISTORY
        String historyQuery = "INSERT INTO TRANSACTION_HISTORY (username, amount_before, current_balance, amount, transaction_message, txn_date) VALUES (?, ?, ?, ?, ?, NOW())";
        try (PreparedStatement ps = con.prepareStatement(historyQuery)) {
            ps.setString(1, user.getUsername());
            ps.setDouble(2, oldBalance);
            ps.setDouble(3, newBalance);
            ps.setDouble(4, amount);
            ps.setString(5, "AMOUNT DEBITED");
            ps.executeUpdate();
        }

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
