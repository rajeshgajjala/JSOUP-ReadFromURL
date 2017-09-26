package com.example.demo;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SampleProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleProjectApplication.class, args);

		try {
			readFromURL("https://github.com/egis/handbook/blob/master/Tech-Stack.md");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readFromURL(String urlName) throws IOException {
		org.jsoup.nodes.Document doc = Jsoup.connect(urlName).get();
		org.jsoup.select.Elements rows = doc.select("tr");
		
		for (org.jsoup.nodes.Element row : rows) {
			
			org.jsoup.select.Elements columns = row.select("td:eq(0)");
			for (org.jsoup.nodes.Element column : columns) {
				if(StringUtils.isNotBlank(column.text())) {
					System.out.println(column.text());
				}
			}
			
			System.out.println();
			
		}
	}
}
