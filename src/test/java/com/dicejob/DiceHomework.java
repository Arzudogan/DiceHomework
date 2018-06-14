package com.dicejob;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceHomework {

	public static void main(String[] args) throws FileNotFoundException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().fullscreen();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String url = "https://dice.com";
		driver.get(url);
		
		List<String> list = new ArrayList<String>();
		list.add("Auditor");
		list.add("Automation Engineer");
		list.add("Automation Test Engineer");
		list.add("Automation Tester");
		list.add("Java Developer");
		list.add("Junior Developer");
		list.add("Computer Programmer");
		list.add("Recruiter");
		list.add("RF Engineer");
		list.add("RPA Developer");
		list.add("Computer Operator");
		list.add("Computer Technician");
		list.add("Consultant");
		list.add("Business Analyst");
		list.add("Business Development");
		list.add("Business Development Manager");
		list.add("Software Engineer");
		list.add("Selenium Automation");
		list.add("SDET");
		list.add("Data Analyst");
		
		String actualTitle =driver.getTitle();
		String expectedTitle ="Job Search for Technology Professionals | Dice.com";
		
		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Step pass. Dice homepage successfully loaded.");
		}else {
			System.out.println("Step fail. Dice homepage did not load successfully");
			throw new RuntimeException("Step fail. Dice homepage did not load successfully");
		}
		
		FileReader fr = new FileReader("jobs.txt");
		BufferedReader br = new BufferedReader(fr);

		String location = "20794";
		List<String> newList = new ArrayList<String>();
		
		for(int i=0; i<list.size(); i++) {
			driver.findElement(By.id("search-field-keyword")).clear();
			driver.findElement(By.id("search-field-keyword")).sendKeys(list.get(i));
			
			driver.findElement(By.id("search-field-location")).clear();
			driver.findElement(By.id("search-field-location")).sendKeys(location);
			
			driver.findElement(By.id("findTechJobs")).click();
			
			newList.add(list.get(i)+"---> " + driver.findElement(By.id("posiCountId")).getText());
			
			driver.navigate().back();
			
		}
		
		driver.close();
		
		for (String string : newList) {
			System.out.println(string.toString()+ " positions avaliable.");
		}
		
		System.out.println("TEST COMPLITED -"+ LocalDateTime.now());
		
	}

}

