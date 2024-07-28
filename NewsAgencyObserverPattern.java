// NewsAgencyObserverPattern.java

import java.util.ArrayList;
import java.util.List;

// Subject interface
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// Observer interface
interface Observer {
    void update(String news);
}

// Concrete Subject
class NewsAgency implements Subject {
    private List<Observer> observers;
    private String news;

    public NewsAgency() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(news);
        }
    }

    public void setNews(String news) {
        this.news = news;
        notifyObservers();
    }
}

// Concrete Observer - Newspaper
class Newspaper implements Observer {
    private String name;

    public Newspaper(String name) {
        this.name = name;
    }

    @Override
    public void update(String news) {
        System.out.println(name + " received news: " + news);
    }
}

// Concrete Observer - TVChannel
class TVChannel implements Observer {
    private String name;

    public TVChannel(String name) {
        this.name = name;
    }

    @Override
    public void update(String news) {
        System.out.println(name + " broadcasting news: " + news);
    }
}

// Concrete Observer - OnlinePlatform
class OnlinePlatform implements Observer {
    private String name;

    public OnlinePlatform(String name) {
        this.name = name;
    }

    @Override
    public void update(String news) {
        System.out.println(name + " posting news online: " + news);
    }
}

// Main class to demonstrate Observer Pattern
public class NewsAgencyObserverPattern {
    public static void main(String[] args) {
        NewsAgency newsAgency = new NewsAgency();

        Newspaper times = new Newspaper("The Times");
        TVChannel bbc = new TVChannel("BBC");
        OnlinePlatform twitter = new OnlinePlatform("Twitter");

        newsAgency.registerObserver(times);
        newsAgency.registerObserver(bbc);
        newsAgency.registerObserver(twitter);

        newsAgency.setNews("New breakthrough in AI technology!");

        System.out.println("-----");

        newsAgency.removeObserver(bbc);
        newsAgency.setNews("New advancements in space travel!");
    }
}
