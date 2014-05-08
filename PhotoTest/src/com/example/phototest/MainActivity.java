package com.example.phototest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Build;
import android.provider.MediaStore;

@SuppressLint("ValidFragment")
public class MainActivity extends ActionBarActivity {

	private static final int REQUEST_CODE_PHOTO = 2314;
	
	private ImageView _imageView;
	private TextView _textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_photo) {
			Log.d("debug", "action photo");
			Intent intent = new Intent();
			intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent, REQUEST_CODE_PHOTO);

			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent)
	{
		super.onActivityResult(requestCode, resultCode, intent);
		Log.d("debug", "onActivityResult");
		
		if (requestCode == REQUEST_CODE_PHOTO)
		{
			if (resultCode == RESULT_OK)
			{
				Bitmap bitmap = intent.getParcelableExtra("data");
				_imageView.setImageBitmap(bitmap);
				save(bitmap);
				Log.d("debug", "OK");
			}
			else if (resultCode == RESULT_CANCELED) 
			{
				Log.d("debug", "CANCELED");
			}
			else
			{
				
			}
		}
	}
	
	private void save(Bitmap bitmap)
	{
		File imageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		if (imageDir.exists() == false)
		{
			imageDir.mkdirs();
		}
		
		File imageFile = new File(imageDir, "photo.png");
		try
		{
			FileOutputStream fos = new FileOutputStream(imageFile);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.flush();
			fos.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		_textView.setText(imageFile.getPath());
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			_imageView = (ImageView) rootView.findViewById(R.id.imageView1);
			_textView = (TextView) rootView.findViewById(R.id.textView1);
			return rootView;
		}
	}

}