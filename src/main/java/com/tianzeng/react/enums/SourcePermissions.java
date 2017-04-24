package com.tianzeng.react.enums;

/**
 * Created by tianzeng on 17-4-22.
 */
public enum SourcePermissions {
    BROWSE("GET"),
    CREATE("POST"),
    UPDATE("PUT"),
    DELETE("DELETE");

    private String code;
    private SourcePermissions(String code){
        this.code=code;
    }
    @Override
    public String toString(){
        return code;
    }
    public static SourcePermissions parseCode(String code){
        for(SourcePermissions s:SourcePermissions.values()){
            if(s.code.equalsIgnoreCase(code))return s;
        }
        return null;
    }

}
