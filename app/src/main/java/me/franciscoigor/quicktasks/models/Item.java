package me.franciscoigor.quicktasks.models;

import java.util.HashMap;
import java.util.UUID;

public abstract class Item {

    private String uuid;
    private HashMap<String,String> values;

    public Item(){
        uuid= UUID.randomUUID().toString();
        values= new HashMap<String,String>();
    }

    public String getUuid() {
        return uuid;
    }

    public void setValue(String key, String value){
        values.put(key,value);
    }

    public String getValue(String key,String defaultValue){
        return values.get(key)==null ? defaultValue: values.get(key);
    }

    public void setValue(String key,boolean value){
        setValue(key,value?"1":"");
    }

    public boolean getBooleanValue(String key,boolean defaultValue){
        return getValue(key,"").equals("1");
    }

    @Override
    public String toString() {
        return "Item[" + getUuid() + "] " + values.toString();
    }


}
