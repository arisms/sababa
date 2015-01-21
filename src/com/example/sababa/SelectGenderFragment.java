package com.example.sababa;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SelectGenderFragment extends Fragment {
private static final String TAG = "SelectGenderFragment";
	
	// Member Data
	Button malePlayer;
	Button femalePlayer;
	SelectGenderFragmentListener mListener;
	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	Log.d(TAG, "onCreateView()");
        // Inflate the layout for this fragment
    	View view =  inflater.inflate(R.layout.selectgender_fragment, container, false);
        
        // Buttons
    	malePlayer = (Button) view.findViewById(R.id.male_button);
        malePlayer.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			mListener.onMalePlayer();
    		}
    	});
        femalePlayer = (Button) view.findViewById(R.id.female_button);
        femalePlayer.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			mListener.onFemalePlayer();
    		}
    	});
        
        return view;
    }
    
    @Override
	public void onAttach(Activity activity) {
		Log.d(TAG, "onAttach()");
	    super.onAttach(activity);
	    if (activity instanceof SelectGenderFragmentListener) {
	    	mListener = (SelectGenderFragmentListener) activity;
	    } else {
	      throw new ClassCastException(activity.toString()
	          + " must implemenet HomeFragment.OnButtonSelectedListener");
	    }
	  }

    public interface SelectGenderFragmentListener {
		
		public void onMalePlayer();
		
		public void onFemalePlayer();
		
	}
}
