package org.example;


public class Main {
    public static void main(String[] args) {
        if (args != null && args.length > 0) {
            databaseConn.initializeDataSource(args[0]);
        } else {
            databaseConn.initializeDataSource(null);
        }


        App.main(args);


    }
}