package com.sdut.laboratorybackmodule.util.file;

public enum FIleType {
    WELCOME_TEXT("WELCOME_TEXT"),
    NEWS_IMAGE("NEWS_IMAGE");
    private final String value;
    FIleType(String value){
        this.value = value;
    }

    public String getPath(){
        return switch (value) {
            case "WELCOME_TEXT" -> "home/";
            case "NEWS_IMAGE" -> "newsImage/";
            default -> "";
        };
    }
}
