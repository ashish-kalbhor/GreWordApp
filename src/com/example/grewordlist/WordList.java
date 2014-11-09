package com.example.grewordlist;

import com.example.grewordlist.db.SQLHelper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class WordList extends Activity
{
	TableLayout table_layout;
	SQLHelper sqlHelper;
	ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_word_list);
		
		 sqlHelper = new SQLHelper(this);
	
		 table_layout = (TableLayout) findViewById(R.id.tableLayout1);
		
		 new MyAsync().execute();	
	}
	
	private void BuildTable() 
	{
		 sqlHelper.open();
		 Cursor c = sqlHelper.readEntry();
		
		 int rows = c.getCount();
		 int cols = c.getColumnCount();
		
		 c.moveToFirst();
		
		 // outer for loop
		 for (int i = 0; i < rows; i++) 
		 {
			  TableRow row = new TableRow(this);
			  
			  // inner for loop
			  for (int j = 0; j < cols; j++) {
			
			   TextView tv = new TextView(this);
			   //tv.setBackgroundResource(R.drawable.);
			   tv.setGravity(Gravity.CENTER);
			   tv.setTextSize(18);
			   tv.setPadding(0, 5, 0, 5);
			
			   tv.setText(c.getString(j));
			
			   row.addView(tv);
		  }
		
		  c.moveToNext();
		
		  table_layout.addView(row);
		
		 }
		 sqlHelper.close();
	}
	
	private class MyAsync extends AsyncTask<Void, Void, Void> 
	{
	 @Override
	 protected void onPreExecute() 
	 {
		  super.onPreExecute();
		
		  table_layout.removeAllViews();
		
		  progressDialog = new ProgressDialog(WordList.this);
		  progressDialog.setTitle("Please Wait..");
		  progressDialog.setMessage("Loading...");
		  progressDialog.setCancelable(false);
		  progressDialog.show();
	 }
	
	 @Override
	 protected Void doInBackground(Void... params)
	 {
		  // inserting data
		  sqlHelper.open();
		  sqlHelper.insertData("Aberrant", "Deviating from normal");
		  sqlHelper.insertData("Benevolent", "Expressing goodwill");
		  sqlHelper.insertData("Capricious", "erratic");
		  try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		  return null;
	 }
	
	 @Override
	 protected void onPostExecute(Void result) 
	 {
		  super.onPostExecute(result);
		  BuildTable();
		  progressDialog.dismiss();
	 }
	}
}
