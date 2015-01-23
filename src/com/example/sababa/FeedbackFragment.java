package com.example.sababa;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FeedbackFragment extends Fragment {
private static final String TAG = "FeedbackFragment";
	
	// Member Data
	Button smiley1Button;
	Button smiley2Button;
	Button smiley3Button;
	FeedbackFragmentListener mListener;
	MainActivity mActivity;
	TextView question;
	FileOutputStream stream;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	Log.d(TAG, "onCreateView()");
        // Inflate the layout for this fragment
    	View view =  inflater.inflate(R.layout.feedback_fragment, container, false);
        mActivity = (MainActivity) getActivity();
    	
        // Buttons
        smiley3Button = (Button) view.findViewById(R.id.smiley3_button);
        smiley3Button.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			mListener.onPositive();
    		}
    	});
        smiley1Button = (Button) view.findViewById(R.id.smiley1_button);
        smiley1Button.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			mListener.onNeutral();
    		}
    	});
        smiley2Button = (Button) view.findViewById(R.id.smiley2_button);
        smiley2Button.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			mListener.onNegative();
    		}
    	});
        
        question = (TextView) view.findViewById(R.id.feedbackquestion_text);
        question.setTypeface(mActivity.nanumBrushScript);
        question.setText("How do you like our App?");
        
        return view;
    }
    
    
    @Override
	public void onAttach(Activity activity) {
		Log.d(TAG, "onAttach()");
	    super.onAttach(activity);
	    if (activity instanceof FeedbackFragmentListener) {
	    	mListener = (FeedbackFragmentListener) activity;
	    } else {
	      throw new ClassCastException(activity.toString()
	          + " must implemenet HomeFragment.OnButtonSelectedListener");
	    }
	  }
    
    @Override
    public void onResume() {
    	super.onResume();
    	Log.d(TAG, "onResume()");
    	//activity.mBubbleFragment.setBubble("Home");
    }
    
    public void recordFeedback(String feedback) {
    	Log.d(TAG, "recordFeedback() - " + feedback);
//    	int time = (int) (System.currentTimeMillis());
//    	Timestamp tsTemp = new Timestamp(time);
//    	String ts =  tsTemp.toString();
    	
//    	Calendar c = Calendar.getInstance(); 
//    	//int seconds = c.get(Calendar.SECOND);
//    	c.
    	
    	Time now = new Time();
    	now.setToNow();
    	
    	 try
    	   {
    	                            // Creates a trace file in the primary external storage space of the 
    	                            // current application.
    	                            // If the file does not exists, it is created.
    	   File feedbackFile = new File(mActivity.getExternalFilesDir(null), "feedback.txt");
    	   if (!feedbackFile.exists())
    		   feedbackFile.createNewFile();
    	                            // Adds a line to the trace file
    	   BufferedWriter writer = new BufferedWriter(new FileWriter(feedbackFile, true /*append*/));
    	   writer.write(now + " - " + feedback + "\n");
    	   writer.close();
    	                           // Refresh the data so it can seen when the device is plugged in a
    	                           // computer. You may have to unplug and replug the device to see the 
    	                           // latest changes. This is not necessary if the user should not modify
    	                           // the files.
    	    MediaScannerConnection.scanFile(mActivity,
    	                                     new String[] { feedbackFile.toString() },
    	                                     null,
    	                                     null);
    	   
    	    }
    	catch (IOException e)
	    {
    		Log.e("com.cindypotvin.FileTest", "Unable to write to the feedback.txt file.");
	    }
	}
    

    public interface FeedbackFragmentListener {
		
		public void onPositive();
		
		public void onNeutral();
		
		public void onNegative();
		
	}
}
