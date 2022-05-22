package com.henrixlt.visma2022internship.exception;


import java.util.function.Supplier;

public class MeetingException  extends RuntimeException  {
    public MeetingException(Long id){
        super("Meeting with this id not find "+id);
    }
    public MeetingException(String name){
        super("you not allow to delete "+name);
    }
    public MeetingException(){
        super("Meeting do not exist");
    }
}
