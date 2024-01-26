package com.example;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Spider {

	// TODOs: Copy freely, Iterate downwards using ID-based URLs.

	private static final String URL_TEMPLATE = "https://portal.ufsm.br/projetos/publico/projetos/view.html?idProjeto=%d";

	public static void main(String... args) {
		Note.warn("Spider: Starting to crawl");
		crawlDescendingIds(74584); // 74584 100000
	}

	private static void crawlDescendingIds(int numb) {

		System.out.println("-------------------------");
		String url = String.format(URL_TEMPLATE, numb);
		Document doc = request(url);

		Note.info("Found PageId: " + numb); // TODOs:

		if (doc == null) {
			crawlDescendingIds(--numb);
      return;
		}

		Boolean isProjectConfidential = doc
				.getElementsByClass("label pill error")
				.text()
				.equals("Este é um projeto confidencial");

		if (isProjectConfidential) {
			Note.fail("Confidential Project: " + url);
			crawlDescendingIds(--numb);
		}

		Note.done("Visiting URL: " + url + ", Title: " + doc.title());

		Elements els = doc.getElementsByClass("span12");
		CollectProjectBasicData(els, doc);
		// CollectProjectParticipants(els, doc);
		// CollectProjectOrgs(els, doc);

		while (true) {
			crawlDescendingIds(--numb);
		}
	}


	private static void CollectProjectBasicData(Elements elements, Document doc) {
		for (Element el : elements) {
			System.out.println(el.child(doc.getElementsByClass("span12").size() % 2).text().toUpperCase()/* .text() */ );
			System.out.println(el.child(doc.getElementsByClass("span12").size() % 2 + 2).text()/* .text() */ + "\n");
		}
	}

	// 74441 PROBLEMA

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