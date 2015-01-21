package com.example.sababa;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {
	private static final String TAG = "HomeFragment";
	
	// Member Data
	Button singlePlayer;
	Button playOnline;
	Button settings;
	HomeFragmentListener mListener;
	MainActivity mActivity;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	Log.d(TAG, "onCreateView()");
        // Inflate the layout for this fragment
    	View view =  inflater.inflate(R.layout.home_fragment, container, false);
        mActivity = (MainActivity) getActivity();
    	
    	
        // Buttons
        singlePlayer = (Button) view.findViewById(R.id.singleplayer);
        singlePlayer.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			mListener.onSinglePlayerButton();
    		}
    	});
        playOnline = (Button) view.findViewById(R.id.playonline);
        playOnline.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			mListener.onPlayOnlineButton();
    		}
    	});
        settings = (Button) view.findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			mListener.onSettingsButton();
    		}
    	});
        
        return view;
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	Log.d(TAG, "onResume()");
    	mActivity.mBubbleFragment.setBubble("Home");
    }
    
    @Override
	public void onAttach(Activity activity) {
		Log.d(TAG, "onAttach()");
	    super.onAttach(activity);
	    if (activity instanceof HomeFragmentListener) {
	    	mListener = (HomeFragmentListener) activity;
	    } else {
	      throw new ClassCastException(activity.toString()
	          + " must implemenet HomeFragment.OnButtonSelectedListener");
	    }
	  }

    public interface HomeFragmentListener {
		
		public void onSinglePlayerButton();
		public void onPlayOnlineButton();
		public void onSettingsButton();
		
	}
}
