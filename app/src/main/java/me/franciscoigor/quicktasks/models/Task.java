package me.franciscoigor.quicktasks.models;

public class Task extends Item {

    public static final String FIELD_TITLE = "title";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_COMPLETED = "completed";

    public Task(String title,String description, boolean completed){
        super();
        setValue(FIELD_TITLE, title);
        setValue(FIELD_DESCRIPTION,description);
        setValue(FIELD_COMPLETED,completed);
    }

    public String getTitle(){
        return getValue(FIELD_TITLE,"");
    }

    public String getDescription(){
        return getValue(FIELD_DESCRIPTION,"");
    }

    public boolean isCompleted(){
        return getBooleanValue(FIELD_COMPLETED, false);
    }

    public void setCompleted(boolean completed){
        setValue(FIELD_COMPLETED, completed);
    }

    public void setTitle(String title){
        setValue(FIELD_TITLE, title);
    }

    public void setDescription(String description){
        setValue(FIELD_DESCRIPTION, description);
    }




}
