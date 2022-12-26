package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class DemoApplication {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("props.xml");
		SpringApplication.run(DemoApplication.class, args);
		MyCLI myCLI = (MyCLI) context.getBean("myCLI");
		AccountBasicCLI accountBasicCLI = (AccountBasicCLI) context.getBean("accountBasicCLI");
		System.out.println("Welcome to CLI bank service");
		System.out.println("Enter operation number:" +
				"\n1 - show accounts" +
				"\n2 - create account" +
				"\n3 - deposit" +
				"\n4 - withdraw" +
				"\n5 - transfer" +
				"\n6 - this message" +
				"\n7 - exit");
		Scanner scanner = new Scanner(System.in);
		loop:
		while (true) {
			switch (scanner.nextInt()) {
				case 1:
					accountBasicCLI.getAccounts("1");
					break;
				case 2:
					accountBasicCLI.createAccountRequest("1");
					break;
				case 7:
					System.out.println("Application Closed");
					break loop;
				default:
					System.out.println("Incorrect command");

			}
		}
		scanner.close();
	}
}
