package net.xsmile.game;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GuessStart extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.gngstart);
        
        setViews();
        setListeners();
        setAnimation();
        setDialog();
        
        

	}
	private Dialog gngDialog;
	private TextView dialogContent;
	private ImageView dialogImage;
	
	private void setDialog(){
		gngDialog = new Dialog(this);
    	gngDialog.setContentView(R.layout.gngdialog);
    	dialogContent = (TextView)gngDialog.findViewById(R.id.dialogText);
    	dialogImage = (ImageView)gngDialog.findViewById(R.id.dialogImage);
    	gngDialog.setTitle("关于本游戏");
    	dialogContent.setText(getString(R.string.info_content));
    	dialogImage.setImageResource(R.drawable.dialog);
	}
	
	
	private ImageView halfbg;
	private Button button_start;
	private Button button_about;
	
	private void setViews(){
		halfbg=(ImageView)findViewById(R.id.halfbg);
		button_start=(Button)findViewById(R.id.start);
		button_about=(Button)findViewById(R.id.about);	
	}
	
	private void setListeners(){
		button_start.setOnClickListener(startGame);
		button_about.setOnClickListener(aboutGame);
	}
	
	private Button.OnClickListener startGame = new Button.OnClickListener()
    {
        public void onClick(View v)
        {
        	Intent myintent = new Intent(getApplicationContext(),GuessNumber.class);
        	startActivity(myintent);
        	finish();
        }
    };
    

	private Button.OnClickListener aboutGame = new Button.OnClickListener()
    {
        public void onClick(View v)
        {
        	gngDialog.show();
        }
    }; 
    
    
    //set
    private Animation halfbg_Translate;
    
    private void setAnimation(){
    	halfbg_Translate= AnimationUtils.loadAnimation(this,R.anim.halfbg_translate_action);
    	halfbg.startAnimation(halfbg_Translate);
    }

}
