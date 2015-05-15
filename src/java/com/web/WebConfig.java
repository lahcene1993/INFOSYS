package com.web;
public class WebConfig {
    
    private static String driver,url,username,password;

    private static String jspDirectiry="";
    
    private static String dataDirectory="";
    
    public static String getDriver() {
        return driver;
    }

    public static void setDriver(String driver) {
        WebConfig.driver = driver;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        WebConfig.url = url;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        WebConfig.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        WebConfig.password = password;
    }

    public static String getJspDirectiry() {
        return jspDirectiry;
    }

    public static void setJspDirectiry(String jspDirectiry) {
        WebConfig.jspDirectiry = jspDirectiry;
    }

    public static String getDataDirectory() {
        return dataDirectory;
    }

    public static void setDataDirectory(String dataDirectory) {
        WebConfig.dataDirectory = dataDirectory;
    }
}