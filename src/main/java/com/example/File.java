package com.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class File {

    public static void saveFile(List<Book> book_list, String path)  {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Book books : book_list) writer.write(books.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Book> readFile(String path)  {


        List<Book> book_list = new ArrayList<>();
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            while ((line = reader.readLine()) != null) {

                String title = (String) line.subSequence(0, line.length() - 7);
                float price = Float.parseFloat(line.substring(line.length() - 5));

                var book = Book.builder()
                        .price(price)
                        .title(title)
                        .build();

                book_list.add(book);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return book_list;
    }
}
