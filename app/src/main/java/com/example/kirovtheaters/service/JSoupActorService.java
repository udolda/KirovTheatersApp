package com.example.kirovtheaters.service;

import com.example.kirovtheaters.model.Actor;
import com.example.kirovtheaters.model.Theater;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.ArrayList;

public class JSoupActorService extends JSoupService {

    private static JSoupActorService instance;
    private JSoupActorService(Theater theater) throws IOException {
        super(theater);
    }

    public static JSoupActorService getInstance(Theater theater) throws IOException {
        return instance == null ? new JSoupActorService(theater) : instance;
    }

    public ArrayList<Actor> getSpasskyTheaterActorsList() {

        ArrayList<Actor> actors = new ArrayList<>();
        Element actorsInfo = document.getElementsByClass("t_user").first();
        for (Element actorInfo : actorsInfo.getElementsByAttributeValueStarting("class", "td")){
            actors.add(new Actor(actorInfo.getElementsByTag("p").get(1)
                    .getElementsByTag("a")
                    .text(),
                    url + actorInfo.getElementsByTag("img").attr("src")));
        }
        return actors;
    }

    public ArrayList<Actor> getDramaTheaterActorsList() {
        ArrayList<Actor> actors = new ArrayList<>();
        Element actorsInfo = document.getElementsByClass("t_person_list").first();

        for (Element actorInfo : actorsInfo.getElementsByAttributeValueStarting("href", "/person/")){
            Element info = actorInfo.getElementsByTag("img").first();
            if (info != null) actors.add(new Actor(info.attr("alt"),
                    url + info.attr("src")));
        }

        return actors;
    }

    public ArrayList<Actor> getDollTheaterActorsList() {
        return null;
    }
}
