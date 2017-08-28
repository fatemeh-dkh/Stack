package com.example.fatemeh_d.stack.Model;



public class Question {
    String question ;
    String proImLink ;
    boolean isAnswered ;
    String genre1 ;
    int score ;
    String Link ;
    String text ;

    public Question(String question, String proImLink, boolean isAnswered, String genre1, int score, String link, String text) {
        this.question = question;
        this.proImLink = proImLink;
        this.isAnswered = isAnswered;
        this.genre1 = genre1;
        this.score = score;
        Link = link;
        this.text = text;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    public String getGenre1() {
        return genre1;
    }

    public void setGenre1(String genre1) {
        this.genre1 = genre1;
    }
    public String getProImLink() {
        return proImLink;
    }

    public void setProImLink(String proImLink) {
        this.proImLink = proImLink;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }
}
