package org.example.discoversuceava;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import database.DatabaseWorker;
import drawer.SettingsActivity;
import android.support.v7.app.ActionBarActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        DatabaseWorker db = new DatabaseWorker(this); 

        String wt = getResources().getString(R.string.welcome_text);
        String sk = getResources().getString(R.string.skip);
        SpannableString ss = new SpannableString(wt);
        SpannableString ss1 = new SpannableString(sk);
        
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        };
        ss.setSpan(clickableSpan, 73, 80, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        
        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
            	AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        		builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);
        		
        		builder.setPositiveButton(R.string.sign_up_button, new DialogInterface.OnClickListener() {
        	           public void onClick(DialogInterface dialog, int id) {
        	        	   Intent i = new Intent(MainActivity.this, SignUpActivity.class);
        	        	   startActivity(i);
        	           }
        	       });
        		
        		builder.setNeutralButton(R.string.sign_in_button, new DialogInterface.OnClickListener() {
     	           public void onClick(DialogInterface dialog, int id) {
     	        	   Intent i = new Intent(MainActivity.this, SignInActivity.class);
     	        	   startActivity(i);
     	           }
     	       });
        		
        		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
        	           public void onClick(DialogInterface dialog, int id) {
        	        	   dialog.cancel();
        	           }
        	       });
        		AlertDialog dialog = builder.create();
        		dialog.show();
            }
        };
        ss.setSpan(clickableSpan2, 65, 72, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        
        ClickableSpan clickableSpan3 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
            }
        };
        ss1.setSpan(clickableSpan3, 0, 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        
        TextView welcomeText = (TextView) findViewById(R.id.welcomeText);
        welcomeText.setText(ss);
        
        welcomeText.setGravity(Gravity.CENTER);
        welcomeText.setPadding(0, 0, 10, 0);
        welcomeText.setMovementMethod(LinkMovementMethod.getInstance());
        
        TextView skip = (TextView)findViewById(R.id.skip);
        skip.setText(ss1);
        skip.setMovementMethod(LinkMovementMethod.getInstance());
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
        if(id == R.id.actions_exit){
			finish();
		}
        return super.onOptionsItemSelected(item);
    }
    
    public void CopyDB(InputStream inputStream, OutputStream outputStream)
  	      throws IOException {
  	    byte[] buffer = new byte[1024];
  	    int length;
  	    while ((length = inputStream.read(buffer)) > 0) {
  	      outputStream.write(buffer, 0, length);
  	    }
  	    inputStream.close();
  	    outputStream.close();
  	  }
}
