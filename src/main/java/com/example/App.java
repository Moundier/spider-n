package com.example;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.HttpStatusException; // Add this import statement

public class App {

	private static final String URL_TEMPLATE = "https://portal.ufsm.br/projetos/publico/projetos/view.html?idProjeto=%d";

	public static void main(String... args) {
		crawlDescending(100000);
	}

	private static void crawlDescending(int numb) {
		String url = String.format(URL_TEMPLATE, numb);
		Document doc = request(url);

		if (doc == null) {
			System.out.println("FAIL: Unable to retrieve document for URL: " + url);
		}

		System.out.println("INFO: Visiting URL - " + url);

		if (doc != null) {
			System.out.println("Title: " + doc.title());
		}

		System.out.println(numb);

		for (int i = 0; numb >= i; numb--) {
			crawlDescending(--numb);
		}
	}

	private static Document request(String url) {
		try {
			Connection connection = Jsoup.connect(url);
			Connection.Response response = connection.execute();
			Document document = response.parse();
			return document;
		} catch (HttpStatusException e) {
			String errorMessage = (e.getStatusCode() == 500) ? "(url)" : "";
			String urlNumb = e.getUrl().substring(40);
			System.out.println("FAIL: " + errorMessage + " " + urlNumb);
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}