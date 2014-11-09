package com.example.grewordlist.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLHelper {

	 private DAO dbhelper;
	 private Context context;
	 private SQLiteDatabase database;
	
	 public SQLHelper(Context c) 
	 {
		 context = c;
	 }
	
	 public SQLHelper open() throws SQLException 
	 {
		 dbhelper = new DAO(context);
		 database = dbhelper.getWritableDatabase();
		 return this;
	 }
	
	 public void close() 
	 {
		 dbhelper.close();
	 }
	
	 public void insertData(String word, String meaning) 
	 {
		 ContentValues cv = new ContentValues();
		 cv.put(DAO.VOCAB_WORD, word);
		 cv.put(DAO.VOCAB_MEANING, meaning);
		 database.insert(DAO.TABLE_VOCAB, null, cv);
	 }
	
	 public Cursor readEntry() 
	 {
		 String[] allColumns = new String[] { 
				 DAO.VOCAB_ID, 
				 DAO.VOCAB_WORD,
				 DAO.VOCAB_MEANING };
	
		 Cursor c = database.query(DAO.TABLE_VOCAB, allColumns, null, null, null, null, null);
	
		 if (c != null) 
		 {
			 c.moveToFirst();
		 }
		 return c;
	 }
}
