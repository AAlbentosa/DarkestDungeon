package main;

import java.awt.EventQueue;

import manager.Manager;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager manager = new Manager();
					manager.init();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
