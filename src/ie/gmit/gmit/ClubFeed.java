package ie.gmit.gmit;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.os.Build;

/*
 * This activity takes in a url to load into its webview and a name to display
 * For now it loads the SU socities list, but this will be updated
 * This webView implements zoom
 */

public class ClubFeed extends Activity {
	WebView webView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		Intent i = getIntent();
		String name = i.getStringExtra("name");
		String link = i.getStringExtra("link");
		setTitle(name);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_club_feed);

		webView = (WebView) findViewById(R.id.clubFeed);  
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setUseWideViewPort(true);
		
		webView.getSettings().setBuiltInZoomControls(true);
		webView.getSettings().setDisplayZoomControls(false);
		
		webView.setWebViewClient(new PortalClient());
		try{
			webView.loadUrl(link);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// this lets the user go back a webpage, otherwise go back an activity
	@Override
	public void onBackPressed() {
		if(webView.canGoBack()) webView.goBack();
		else super.onBackPressed();
	}	
	
	// this loads the url into our own webview (overrides the default device launcher)
	private class PortalClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.su_feed, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case android.R.id.home:
			// this is called when the Home (up) button is pressed in the action bar
			Intent parentActivityIntent = new Intent(this, ClubActivity.class);
			parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(parentActivityIntent);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
