package com.lgt.beans;


import lombok.Data;


@Data
public class Novel {
    private int novelId;
    private String name;
    private String author;
    private String description;
    private String cover;

    @Override
    public String toString() {
        return "Novel{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
