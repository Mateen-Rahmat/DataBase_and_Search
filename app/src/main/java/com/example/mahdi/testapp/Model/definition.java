package com.example.mahdi.testapp.Model;

public class definition {

    public int id;
    public String word, meaning;

    public definition(int id, String word, String meaning)
    {
        this.id = id;
        this.word = word;
        this.meaning = meaning;
    }

    public definition()
    {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
    public String getMeaning() {
        return meaning;
    }


}
