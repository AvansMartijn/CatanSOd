package controller;

import dbaccess.AccountDA;
import dbaccess.ChatDA;
import dbaccess.MainDA;

public class Main {

	public static void main(String[] args) {
		MainDA mainDA = new MainDA();
		mainDA.loadDataBaseDriver();
		
		mainDA.createChatDA();
		ChatDA chatDA = mainDA.getChatDA();
		
		mainDA.createAccountDA();
		AccountDA accountDA = mainDA.getAccountDA();
		
		chatDA.addMessage(1, "Het account maken werkt!");
		mainDA.selectQuery();
		
		chatDA.getMessages();
		
		accountDA.createAccount("Martijn", "Heeftditgemaakt");
		
		if(accountDA.login("Chiel", "Beelen")) {
			System.out.println("Successful login");
		} else { 
			System.out.println("Wrong password or username");
		}
		
		if(accountDA.login("Chiel", "C")) {
			System.out.println("Successful login");
		} else { 
			System.out.println("Wrong password or username");
		}
		
		if(accountDA.login("Martijn", "Heeftditgemaakt")) {
			System.out.println("Successful login");
		} else { 
			System.out.println("Wrong password or username");
		}
		
	}

}
