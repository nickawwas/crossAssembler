package main.java;

import main.interfaces.IComment;

public class Comment implements IComment {
    private final String comment;

    //Default constructor
    public Comment(){
        comment = "";
    }

    //Parametrized constructor
    public Comment(String cm){
        comment = cm;
    }

    //Return a comment
    public String getCmt(){
        return comment;
    }


}