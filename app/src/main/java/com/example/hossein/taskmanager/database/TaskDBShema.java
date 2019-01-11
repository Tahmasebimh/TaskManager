package com.example.hossein.taskmanager.database;

public class TaskDBShema {

    //data base name
    public static final String NAME = "taskManager.db";
    public static final int VERSION = 1 ;


    //table name
    public static final class TaskTable {
        public static final String NAME = "task" ;

        //column name
        public static final class Cols{
            //name of data column
            public static final String ID = "_id" ;
            public static final String UUID = "uuid" ;
            public static final String TITLE = "title" ;
            public static final String DATE = "date" ;
            public static final String DESCRYPTION = "descryption" ;
            public static final String ISDONE = "isdone" ;
            public static final String ACCOUNTID = "accountid" ;

        }
    }

    public static final class AccountTable {

        //table name
        public static final String NAME = "account" ;

        //column name
        public static final class Cols{
            //name of data column
            public static final String ID = "_id" ;
            public static final String USERNAME = "username" ;
            public static final String PASSWORD = "password" ;

        }
    }
}
