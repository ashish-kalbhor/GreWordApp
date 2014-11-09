package com.example.grewordlist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAO extends SQLiteOpenHelper {

	 // TABLE INFORMATTION
	 public static final String TABLE_VOCAB = "vocab";
	 public static final String VOCAB_ID = "_id";
	 public static final String VOCAB_WORD = "word";
	 public static final String VOCAB_MEANING = "meaning";
	
	 // DATABASE INFORMATION
	 static final String DB_NAME = "VOCAB.DB";
	 static final int DB_VERSION = 1;
	
	 // TABLE CREATION STATEMENT
	
	 private static final String CREATE_TABLE = "create table " + TABLE_VOCAB
	   + "(" + VOCAB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	   + VOCAB_WORD + " TEXT NOT NULL ," + VOCAB_MEANING
	   + " TEXT NOT NULL);";
	
	 public DAO(Context context) {
	  super(context, DB_NAME, null, DB_VERSION);
	
	 }
	
	 @Override
	 public void onCreate(SQLiteDatabase db) {
	
	  db.execSQL(CREATE_TABLE);
	 }
	
	 @Override
	 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
	  db.execSQL("DROP TABLE IF EXISTS " + TABLE_VOCAB);
	  onCreate(db);
	
	 }

}
