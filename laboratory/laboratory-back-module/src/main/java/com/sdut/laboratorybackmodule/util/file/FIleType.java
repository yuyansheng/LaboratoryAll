package com.sdut.laboratorybackmodule.util.file;

public enum FileType {
    WELCOME_TEXT("WELCOME_TEXT"),
    NEWS_IMAGE("NEWS_IMAGE"),
    README_TEXT("README_TEXT"),
    SOFTWARE("SOFTWARE");
    private final String value;
    FileType(String value){
        this.value = value;
    }

    public String getPath(){
        return switch (value) {
            case "WELCOME_TEXT" -> "home/";
            case "NEWS_IMAGE" -> "newsImage/";
            case "README_TEXT" -> "readme/";
            case "SOFTWARE" -> "software/";
            default -> "";
        };
    }
}
