package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


public class Main {

    private static final String save_books = "E:/no-scraping-bro-java/src/main/resources/old.txt";

    public static void log(String s) {
        System.out.print(s);
    }

    public static void log_l(String s) {
        System.out.println(s);
    }

    private static boolean regexOnTitle(String match) throws PatternSyntaxException {
        Pattern p = Pattern.compile("^(The).*$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(match);

        if (m.find()) {
            log_l("\033[32m" + "[FOUND!]: " + "\033[0m" + "regex pattern was spotted!");
            return true;
        } else {
            log_l("\u001B[31m" + "[ERROR!]: " + "\u001B[0m" + "regex was not spot!");
            return false;
        }
    }

    public static void jsoupInit() throws IOException, NumberFormatException {

        List<Book> newer = new ArrayList<>();

        String url = "https://books.toscrape.com/catalogue/page-2.html";
        Document doc = Jsoup.connect(url).get();
        Elements article_list = doc.getElementsByTag("article");

        for (Element each_article : article_list) {

            float price = 0;
            String title;

            // TODO: price
            Elements price_class_list = each_article.getElementsByClass("price_color");
            for (Element e_price : price_class_list) {

                log("Price: " + e_price.text());
                StringBuilder sb = new StringBuilder();
                sb.append(e_price.text());
                sb.delete(0, 1); // todo: remove £
                sb.append("F");  // todo: float literal
                price = Float.parseFloat(String.valueOf(sb));
            }

            // TODO: stock
            // Elements in_stock_list = each_article.getElementsByClass("instock availability");
            // log(" Stock: " + in_stock_list.text());

            // TODO: score
            // Elements star_rating_tree_list = each_article.getElementsByClass("star-rating");
            // log(" Rating: " + star_rating_tree_list.attr("class"));

            // TODO: title
            Elements h3_tag_list = each_article.getElementsByTag("h3");
            for (Element each_h3_tag : h3_tag_list) {

                Elements a_tag_list = each_h3_tag.children();
                for (Element each_title : a_tag_list) {

                    log(" -> " + each_title.attr("title") + " -> ");
                    boolean patternCorresponds = regexOnTitle(each_title.attr("title"));

                    if (patternCorresponds) {
                        title = each_title.attr("title");

                        // TODO: build book
                        Book book = Book.builder()
                                .price(price)
                                .title(title)
                                .build();

                        newer.add(book);
                    }

                    break;
                }
            }
        }

        // TODO: read from file
        List<Book> older = File.readFile(save_books);

        // TODO: compare older and newer
        log_l("\nComparing prices");
        if (older.size() != newer.size()) {
            log_l("\u001B[31m" + "[ERROR!]: " + "\u001B[0m" + "older and newer are different.");
        } else {
            for (int i = 0; i < older.size(); ++i) {
                String title = older.get(i).getTitle();
                if (older.get(i).getPrice() < newer.get(i).getPrice()) {
                    log_l("\033[33m" + "good value! " + "\033[0m" + "[" + title + "]" + " New price is " + older.get(i).getPrice() + ", compared with previous " + newer.get(i).getPrice());
                } else if (older.get(i).getPrice() == newer.get(i).getPrice()) {
                    log_l("same price! " + "[" + title + "] " + older.get(i).getPrice() + " is equal to " + newer.get(i).getPrice());
                } else {
                    log_l("\u001B[31m" + "hard price! " + "\u001B[0m" + "[" + title + "] " + older.get(i).getPrice() + " is not equal to " + newer.get(i).getPrice());
                }
            }
        }

        // TODO: save list from jsoup build
        File.saveFile(newer, save_books);
    }

    public static void main(String[] args) throws IOException {
        jsoupInit();
    }

}