package com.example.sababa;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class HeaderFragment extends Fragment {
	private static final String TAG = "BubbleFragment";
	
	ImageView imgView1;
	ImageView imgView2;
	ImageView imgView3;
	
	MainActivity mActivity;
	RelativeLayout layout;
	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	Log.d(TAG, "onCreateView()");
        // Inflate the layout for this fragment
    	View view =  inflater.inflate(R.layout.header_fragment, container, false);
    	mActivity = (MainActivity) getActivity();
    	
    	layout = new RelativeLayout(mActivity);
    	//RelativeLayout.LayoutParams parameters = new RelativeLayout.LayoutParams(source)
        
    	imgView1 = (ImageView)view.findViewById(R.id.bubble_home);
    	imgView2 = (ImageView)view.findViewById(R.id.bubble_question_male);
    	imgView3 = (ImageView)view.findViewById(R.id.bubble_question_female);

    	setBubble("Home");
    	
        return view;
    }

    public void setBubble(String option) {
    	if(option.equals("Home")) {
    		Log.d(TAG, "Visible");
    		imgView1.setVisibility(View.VISIBLE);
    		imgView2.setVisibility(View.INVISIBLE);
    		imgView3.setVisibility(View.INVISIBLE);
    	}
    	else if(option.equals("Male")){
    		Log.d(TAG, "Invisible");
    		imgView1.setVisibility(View.INVISIBLE);
    		imgView2.setVisibility(View.VISIBLE);
    		imgView3.setVisibility(View.INVISIBLE);
    	}
    	else if(option.equals("Female")){
    		Log.d(TAG, "Invisible");
    		imgView1.setVisibility(View.INVISIBLE);
    		imgView2.setVisibility(View.INVISIBLE);
    		imgView3.setVisibility(View.VISIBLE);
    	}
    }
}
