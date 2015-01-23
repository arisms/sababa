package com.example.sababa;

import java.util.List;

import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.sababa.FeedbackFragment.FeedbackFragmentListener;
import com.example.sababa.HomeFragment.HomeFragmentListener;
import com.example.sababa.QuestionsFragment.QuestionsFragmentListener;
import com.example.sababa.SelectGenderFragment.SelectGenderFragmentListener;


public class MainActivity extends FragmentActivity implements HomeFragmentListener, SelectGenderFragmentListener, 
	QuestionsFragmentListener, FeedbackFragmentListener{
	private static final String TAG = "MainActivity";
	
	public Typeface nanumBrushScript;
	public String playerGender;
	public SoundPool soundpool;
	public int soundIds[] = new int[4];
	public int currentQuestionIndex;
	public List<String> mQuestionsList;
	
	HomeFragment mHomeFragment;
	HeaderFragment mHeaderFragment;
	FooterFragment mFooterFragment;
	SelectGenderFragment mSelectGenderFragment;
	QuestionsFragment mQuestionsFragment;
	FeedbackFragment mFeedbackFragment;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	Log.d(TAG, "onCreate()");
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (savedInstanceState == null) {
			// Add the fragments on initial activity setup
        	mHeaderFragment = new HeaderFragment();
        	getSupportFragmentManager().beginTransaction().add(R.id.header_layout, mHeaderFragment).commit();
        	mHomeFragment = new HomeFragment();
			getSupportFragmentManager().beginTransaction().add(R.id.main_layout, mHomeFragment).commit();
			mFooterFragment = new FooterFragment();
			getSupportFragmentManager().beginTransaction().add(R.id.footer_layout, mFooterFragment).commit();
		}
        
        // Other Fragments
        mSelectGenderFragment = new SelectGenderFragment();
        mQuestionsFragment = new QuestionsFragment();
        mFeedbackFragment = new FeedbackFragment();
        
        nanumBrushScript = Typeface.createFromAsset(getAssets(), "NanumBrushScript.ttf");
        
        // Sound Effects
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundpool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundIds[0] = soundpool.load(this, R.raw.buzz, 1);
        soundIds[1] = soundpool.load(this, R.raw.correct, 1);
    }
    
    
    
    public void showFeedback() {
    	Log.d(TAG, "showFeedback()");
    	
    	mHeaderFragment.setBubble("Home");
    	mQuestionsFragment.showFeedback = false;
    	
    	// Load the FeedbackFragment 
    	getSupportFragmentManager().beginTransaction()
			.replace(R.id.main_layout, mFeedbackFragment).commit();
    	
    }
    
    

    /* ************ Fragment Interfaces ************ */
    
    /**
     * HomeFragment  -  onSinglePlayerButton()
     */
    @Override
    public void onSinglePlayerButton() {
    	// When the user taps the Create Game button
    	Log.d(TAG, "onSinglePlayerButton()");
    	
    	// Load the GameSetupFragment 
    	getSupportFragmentManager().beginTransaction()
			.replace(R.id.main_layout, mSelectGenderFragment).addToBackStack(null).commit();
    }
    /**
     * HomeFragment  -  onPlayOnlineButton()
     */
    @Override
    public void onPlayOnlineButton() {
    	// When the user taps the Create Game button
    	Log.d(TAG, "onPlayOnlineButton()");
    }
    /**
     * HomeFragment  -  onSettingsButton()
     */
    @Override
    public void onSettingsButton() {
    	// When the user taps the Create Game button
    	Log.d(TAG, "onSettingsButton()");
    }
    
    /**
     * SelectGenderFragment  -  onMalePlayer()
     */
    @Override
    public void onMalePlayer() {
    	// When the user taps the Create Game button
    	Log.d(TAG, "onMalePlayer()");
    	
    	mHeaderFragment.setBubble("Male");
    	playerGender = "Male";
    	
    	// Load the GameSetupFragment 
    	getSupportFragmentManager().beginTransaction()
			.replace(R.id.main_layout, mQuestionsFragment).addToBackStack(null).commit();
    	
    }

    /**
     * SelectGenderFragment  -  onFemalePlayer()
     */
    @Override
    public void onFemalePlayer() {
    	// When the user taps the Create Game button
    	Log.d(TAG, "onFemalePlayer()");
    	
    	mHeaderFragment.setBubble("Female");
    	playerGender = "Female";
    	
    	// Load the GameSetupFragment 
    	getSupportFragmentManager().beginTransaction()
			.replace(R.id.main_layout, mQuestionsFragment).addToBackStack(null).commit();
    	
    }
    
    /**
     * QuestionsFragment  -  onYesButton()
     */
    @Override
    public void onYesButton() {
    	// When the user taps the Create Game button
    	Log.d(TAG, "onYesButton()");
    	
    	soundpool.play(soundIds[1], (float)0.2, (float)0.2, 1, 0, 1);

    	if(mQuestionsFragment.showFeedback && (currentQuestionIndex % 10 == 0)) {
    		showFeedback();
    	}
    	else if(!mQuestionsFragment.setNextQuestion()) {
    		// If the questions are over, return to home page
    		currentQuestionIndex = 0;
    		getSupportFragmentManager().beginTransaction()
				.replace(R.id.main_layout, mHomeFragment).commit();
    	}
    		
    }
    
    /**
     * QuestionsFragment  -  onNoButton()
     */
    @Override
    public void onNoButton() {
    	// When the user taps the Create Game button
    	Log.d(TAG, "onNoButton()");
    	
    	soundpool.play(soundIds[0], (float)0.2, (float)0.2, 1, 0, 1);
    	
    	if(mQuestionsFragment.showFeedback && (currentQuestionIndex % 10 == 0)) {
    		showFeedback();
    	}
    	else if(!mQuestionsFragment.setNextQuestion()) {
    		// If the questions are over, return to home page
    		currentQuestionIndex = 0;
    		getSupportFragmentManager().beginTransaction()
				.replace(R.id.main_layout, mHomeFragment).commit();
    	}
    }
    
    /**
     * FeedbackFragment  -  onPositive()
     */
    @Override
    public void onPositive() {
    	// When the user taps the Create Game button
    	Log.d(TAG, "onPositive()");
    	
    	mFeedbackFragment.recordFeedback("Positive");
    	
    	if(playerGender.equals("Male")) {
    		mHeaderFragment.setBubble("Male");
        	// Load the GameSetupFragment 
        	getSupportFragmentManager().beginTransaction()
    			.replace(R.id.main_layout, mQuestionsFragment).commit();
    	}
    	else if(playerGender.equals("Female")) {
    		mHeaderFragment.setBubble("Female");
        	// Load the GameSetupFragment 
        	getSupportFragmentManager().beginTransaction()
    			.replace(R.id.main_layout, mQuestionsFragment).commit();
    	}
    	
    	onYesButton();
    }
    
    /**
     * FeedbackFragment  -  onNeutral()
     */
    @Override
    public void onNeutral() {
    	// When the user taps the Create Game button
    	Log.d(TAG, "onNeutral()");
    	
    	mFeedbackFragment.recordFeedback("Neutral");
    	if(playerGender.equals("Male")) {
    		mHeaderFragment.setBubble("Male");
        	// Load the GameSetupFragment 
        	getSupportFragmentManager().beginTransaction()
    			.replace(R.id.main_layout, mQuestionsFragment).commit();
    	}
    	else if(playerGender.equals("Female")) {
    		mHeaderFragment.setBubble("Female");
        	// Load the GameSetupFragment 
        	getSupportFragmentManager().beginTransaction()
    			.replace(R.id.main_layout, mQuestionsFragment).commit();
    	}
    	
    	onYesButton();
    }
    
    /**
     * FeedbackFragment  -  onNegative)
     */
    @Override
    public void onNegative() {
    	// When the user taps the Create Game button
    	Log.d(TAG, "onNegative()");
    	
    	mFeedbackFragment.recordFeedback("Negative");
    	if(playerGender.equals("Male")) {
    		mHeaderFragment.setBubble("Male");
        	// Load the GameSetupFragment 
        	getSupportFragmentManager().beginTransaction()
    			.replace(R.id.main_layout, mQuestionsFragment).commit();
    	}
    	else if(playerGender.equals("Female")) {
    		mHeaderFragment.setBubble("Female");
        	// Load the GameSetupFragment 
        	getSupportFragmentManager().beginTransaction()
    			.replace(R.id.main_layout, mQuestionsFragment).commit();
    	}
    	
    	onYesButton();
    }
}
