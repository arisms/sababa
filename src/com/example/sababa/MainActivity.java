package com.example.sababa;

import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.sababa.HomeFragment.HomeFragmentListener;
import com.example.sababa.QuestionsFragment.QuestionMaleFragmentListener;
import com.example.sababa.SelectGenderFragment.SelectGenderFragmentListener;


public class MainActivity extends FragmentActivity implements HomeFragmentListener, SelectGenderFragmentListener, 
	QuestionMaleFragmentListener{
	private static final String TAG = "MainActivity";
	
	public Typeface nanumBrushScript;
	public String playerGender;
	public SoundPool soundpool;
	public int soundIds[] = new int[4];
	
	HomeFragment mHomeFragment;
	BubbleFragment mBubbleFragment;
	SelectGenderFragment mSelectGenderFragment;
	QuestionsFragment mQuestionsFragment;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	Log.d(TAG, "onCreate()");
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (savedInstanceState == null) {
			// Add the fragments on initial activity setup
        	mBubbleFragment = new BubbleFragment();
        	getSupportFragmentManager().beginTransaction().add(R.id.bubble_layout, mBubbleFragment).commit();
        	mHomeFragment = new HomeFragment();
			getSupportFragmentManager().beginTransaction().add(R.id.main_layout, mHomeFragment).commit();
		}
        
        // Other Fragments
        mSelectGenderFragment = new SelectGenderFragment();
        mQuestionsFragment = new QuestionsFragment();
        
        nanumBrushScript = Typeface.createFromAsset(getAssets(), "NanumBrushScript.ttf");
        
        // Sound Effects
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundpool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundIds[0] = soundpool.load(this, R.raw.buzz, 1);
        soundIds[1] = soundpool.load(this, R.raw.correct, 1);
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
    	
    	mBubbleFragment.setBubble("Male");
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
    	
    	mBubbleFragment.setBubble("Female");
    	playerGender = "Female";
    	
    	// Load the GameSetupFragment 
    	getSupportFragmentManager().beginTransaction()
			.replace(R.id.main_layout, mQuestionsFragment).addToBackStack(null).commit();
    }
    
    /**
     * QuestionMaleFragment  -  onYesButton()
     */
    @Override
    public void onYesButton() {
    	// When the user taps the Create Game button
    	Log.d(TAG, "onYesButton()");
    	
    	soundpool.play(soundIds[1], (float)0.2, (float)0.2, 1, 0, 1);
    	
    	if(!mQuestionsFragment.setNextQuestion()) {
    		// If the questions are over, return to home page
    		mQuestionsFragment.currentQuestionIndex = 0;
    		getSupportFragmentManager().beginTransaction()
				.replace(R.id.main_layout, mHomeFragment).commit();
    	}
    		
    }
    
    /**
     * QuestionMaleFragment  -  onYesButton()
     */
    @Override
    public void onNoButton() {
    	// When the user taps the Create Game button
    	Log.d(TAG, "onNoButton()");
    	
    	soundpool.play(soundIds[0], (float)0.2, (float)0.2, 1, 0, 1);
    	
    	if(!mQuestionsFragment.setNextQuestion()) {
    		// If the questions are over, return to home page
    		mQuestionsFragment.currentQuestionIndex = 0;
    		getSupportFragmentManager().beginTransaction()
				.replace(R.id.main_layout, mHomeFragment).commit();
    	}
    }
}
