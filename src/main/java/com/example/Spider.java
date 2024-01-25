package com.example;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Spider {
  
  // TODOs: Copy freely, Iterate downwards using ID-based URLs.

  private static final String URL_TEMPLATE = "https://portal.ufsm.br/projetos/publico/projetos/view.html?idProjeto=%d";

	public static void main(String... args) {
		Note.warn("Spider: Starting to crawl");
		crawlDescendingIds(74584); // 74584 100000 
	}

	private static void crawlDescendingIds(int numb) {
		System.out.println("----------------------");
		String url = String.format(URL_TEMPLATE, numb);
		Document doc = request(url);
		
		if (doc != null) {
			Note.done("Visiting URL: " + url + ", Title: " + doc.title());
		}

		Note.info("Found PageId: " + numb);

		for (int i = 0; numb >= i;) {
			crawlDescendingIds(--numb); // A for loop that goes down
		}
	}

	private static Document request(String url) {
		try {
			Connection connection = Jsoup.connect(url);
			Connection.Response response = connection.execute();
			Document document = response.parse();
			return document;
		} 
		catch (NullPointerException e) {
			Note.fail("Document is null");
			return null;
		}
		catch (HttpStatusException e) {
			Note.fail("Visiting URL: " + e.getUrl());
			return null;
		} 
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
