package com.example.sababa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class QuestionsFragment extends Fragment {
private static final String TAG = "QuestionsFragment";
	
	// Member Data
	Button yesButton;
	Button noButton;
	QuestionsFragmentListener mListener;
	MainActivity mActivity;
	TextView question;
	public boolean showFeedback = true;
	public boolean startGame = true;
	View view;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	Log.d(TAG, "onCreateView()");
        // Inflate the layout for this fragment
    	view =  inflater.inflate(R.layout.questions_fragment, container, false);
        mActivity = (MainActivity) getActivity();
    	
        // Buttons
    	yesButton = (Button) view.findViewById(R.id.yes_button);
    	yesButton.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			mListener.onYesButton();
    		}
    	});
        noButton = (Button) view.findViewById(R.id.no_button);
        noButton.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			mListener.onNoButton();
    		}
    	});
        
        question = (TextView) view.findViewById(R.id.question_text);
        question.setTypeface(mActivity.nanumBrushScript);
        
        if(startGame) {
        	startGame();
        
        	// Show the first question
        }
        setNextQuestion();
        
        return view;
    }
    
    public void startGame() {
    	Log.d(TAG, "startGame()");
    	// Read the questions from the txt file and store them in a list
        BufferedReader reader = null;
        mActivity.mQuestionsList = new ArrayList<String>();
        try {
        		if(mActivity.playerGender.equals("Male")) {
        			reader = new BufferedReader(new InputStreamReader(getActivity().getAssets().
        					open("male_questions.txt"), "UTF-8"));
        		}
        		else if(mActivity.playerGender.equals("Female")) {
        			reader = new BufferedReader(new InputStreamReader(getActivity().getAssets().
        					open("female_questions.txt"), "UTF-8"));
        		}
        		String mLine = reader.readLine();
        		while (mLine != null) {
        			mActivity.mQuestionsList.add(mLine);
        			
        			mLine = reader.readLine();
        		}
    	}
        catch (IOException e) {
    		Log.e(TAG, "open txt file error 1" + e);
    	} 
        finally 
        {
    	    if (reader != null) {
    	         try {
    	             reader.close();
    	         } catch (IOException e) {
    	        	 Log.e("DatabaseHelper", "open txt file error 2" + e);
    	         }
    	    }
    	}
        // Randomize order of questions
        Collections.shuffle(mActivity.mQuestionsList);
        
        mActivity.currentQuestionIndex = 0;
        
        startGame = false;
    }
    
    public boolean setNextQuestion() {
    	Log.d(TAG, "setNextQuestion(), size - index:" + mActivity.mQuestionsList.size() + " - " + mActivity.currentQuestionIndex);
    	
    	if(mActivity.currentQuestionIndex < mActivity.mQuestionsList.size()) {
    		question.setText(mActivity.mQuestionsList.get(mActivity.currentQuestionIndex));
    		mActivity.currentQuestionIndex++;
    		showFeedback = true;
    		return true;
    	}
    	return false;
    }
    
    @Override
	public void onAttach(Activity activity) {
		Log.d(TAG, "onAttach()");
	    super.onAttach(activity);
	    if (activity instanceof QuestionsFragmentListener) {
	    	mListener = (QuestionsFragmentListener) activity;
	    } else {
	      throw new ClassCastException(activity.toString()
	          + " must implemenet HomeFragment.OnButtonSelectedListener");
	    }
	  }
    
    @Override
    public void onResume() {
    	super.onResume();
    	Log.d(TAG, "onResume()");
    }

    public interface QuestionsFragmentListener {
		
		public void onYesButton();
		
		public void onNoButton();
		
	}
}
