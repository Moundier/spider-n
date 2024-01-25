package com.example;


import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private float price;
    private String title;

    @Override
    public String toString() {
        return title + ", " + price + "\n";
    }

    // TODO: for future release
    /* private boolean forSale; */
    /* private int rating; */
}