package app.dh.manager.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import app.dh.manager.R;
import app.dh.manager.ui.settings.Settings;


public class SpalshScreen extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spalsh_screen);
		startActivity(new Intent(this, Settings.class));
	}
}
