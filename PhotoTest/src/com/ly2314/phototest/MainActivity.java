package com.ly2314.phototest;

import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	int id = item.getItemId();
    	if (id == R.id.action_photo)
    	{
    		Log.d("debug", "action photo");
    		Intent intent = new Intent();
    		intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
    		startActivity(intent);
    		return true;
    	}
    	return super.onOptionsItemSelected(item);
    }
}
