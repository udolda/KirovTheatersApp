package com.example.kirovtheaters.service;

import com.example.kirovtheaters.model.Theater;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

abstract class JSoupService {

    protected Document document;
    protected String url;

    protected JSoupService(Theater theater) throws IOException {
        document = Jsoup.connect(theater.getTroupe()).get();
        url = theater.getSite();
    }

}
