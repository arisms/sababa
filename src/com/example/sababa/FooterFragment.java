package com.example.sababa;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FooterFragment extends Fragment {
private static final String TAG = "SelectGenderFragment";
	
	// Member Data
	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	Log.d(TAG, "onCreateView()");
        // Inflate the layout for this fragment
    	View view =  inflater.inflate(R.layout.footer_fragment, container, false);
        
        
        return view;
    }
    
    @Override
	public void onAttach(Activity activity) {
		Log.d(TAG, "onAttach()");
	    super.onAttach(activity);
	    
	  }

    public interface FooterFragmentListener {
		
	}
}
