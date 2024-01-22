package com.example;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.HttpStatusException; // Add this import statement

public class App {

	private static final String URL_TEMPLATE = "https://portal.ufsm.br/projetos/publico/projetos/view.html?idProjeto=%d";

	public static void main(String... args) {
		crawlDescending(74584); // 74584 100000 
	}

	private static void crawlDescending(int numb) {
		System.out.println("----------------------");
		String url = String.format(URL_TEMPLATE, numb);
		Document doc = request(url);

		if (doc == null) {
			Dog.fail("Unable to retrieve document for URL: " + url);
		}

		Dog.warn("Visiting URL - " + url);

		if (doc != null) {
			Dog.info("Title: " + doc.title());
		}

		Dog.info("" + numb);

		for (int i = 0; numb >= i; numb--) {
			crawlDescending(--numb);
		}
	}

	private static Document request(String url) {
		try {
			Connection connection = Jsoup.connect(url);
			Connection.Response response = connection.execute();
			Dog.done("Visiting URL -");
			Document document = response.parse();
			return document;
		} catch (HttpStatusException e) {
			String errorMessage = (e.getStatusCode() == 500) ? "Visiting URL -" : "";
			String urlNumb = e.getUrl().substring(40);
			Dog.fail(errorMessage + " " + urlNumb);
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}