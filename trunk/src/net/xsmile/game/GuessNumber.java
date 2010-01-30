package net.xsmile.game;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class GuessNumber extends Activity{
	
	

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置成全屏模式
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE););//强制为横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
        setContentView(R.layout.main);
        
        setViews();
        
        setDialog();

    	GNGhandler.sleep(millis);
    	
    	
    	
    	
    	
    	
    	//happyRobot();

    }
    
    private TextView GNGTime;
    private TextView GNGBar;
	private GuessView GNGTouch;
	private GuessFunView GNGView;
	private TextView GNGResult;
	private ImageView srobot;
	private ImageView hrobot;
	private ImageView brobot;
	
	//private String ts1;
	//private String ts2;
	
	private String successString;
	private String failString;
    
	private void setViews(){
		
		GNGTime = (TextView) findViewById(R.id.TVTime);
		GNGBar= (TextView) findViewById(R.id.TVBar);
	    //GNGTime.setVisibility(View.INVISIBLE);
	    GNGTouch = (GuessView) findViewById(R.id.gng);
	    GNGView=(GuessFunView)findViewById(R.id.gngfun);
	    GNGResult = (TextView) findViewById(R.id.TVResult);
	    //GNGResult.setVisibility(View.INVISIBLE);
	    srobot=(ImageView)findViewById(R.id.srobot);
	    srobot.setVisibility(View.INVISIBLE);
	    hrobot=(ImageView)findViewById(R.id.hrobot);
	    hrobot.setVisibility(View.INVISIBLE);
	    brobot=(ImageView)findViewById(R.id.brobot);
	    brobot.setVisibility(View.INVISIBLE);

	    //ts1=getString(R.string.tstring_left);
	    //ts2=getString(R.string.tstring_right);
	    successString=getString(R.string.success_bar);
	    failString=getString(R.string.fail_bar);
	}
	
	private Dialog gngDialog;
	private TextView dialogContent;
	private ImageView dialogImage;
	
	private void setDialog(){
		gngDialog = new Dialog(this);
    	gngDialog.setContentView(R.layout.gngdialog);
    	dialogContent = (TextView)gngDialog.findViewById(R.id.dialogText);
    	dialogImage = (ImageView)gngDialog.findViewById(R.id.dialogImage);
    	
	}

    private void showResult(int flag){
    	switch (flag){
    	case 0:
    		GNGBar.setText(failString);
    		break;
    	case 1:
    		GNGBar.setText(successString);
    		happyRobot();
    		
    		break;
    	case 2:
    		//GNGTime.setText(""+GNGTouch.getTimeLeft());
    		break;
    	default:
    		//GNGTime.setText(""+GNGTouch.getTimeLeft());
    		break;
    	}
    	GNGTime.setText(""+GNGTouch.getTimeLeft());
    	GNGResult.setText(GNGTouch.getResult());
    }
    
   
    private Animation srobot_Translate;
	private Animation hrobot_Translate;
	private Animation brobot_Translate;
   
    private void happyRobot(){
    	srobot.setVisibility(View.VISIBLE);
    	hrobot.setVisibility(View.VISIBLE);
    	brobot.setVisibility(View.VISIBLE);
    	//ImageView ro=(ImageView)findViewById(R.id.ImageView03);
    	srobot_Translate= AnimationUtils.loadAnimation(this,R.anim.srobot_translate_action);
    	hrobot_Translate= AnimationUtils.loadAnimation(this,R.anim.hrobot_translate_action);
    	brobot_Translate= AnimationUtils.loadAnimation(this,R.anim.brobot_translate_action);
    	//myAnimation_Translate.setRepeatCount(2);
    	//myAnimation_Translate.setRepeatMode(1);
    	srobot.startAnimation(srobot_Translate);
    	srobot.setVisibility(View.INVISIBLE);
    	hrobot.startAnimation(hrobot_Translate);
    	hrobot.setVisibility(View.INVISIBLE);
    	brobot.startAnimation(brobot_Translate);
    	brobot.setVisibility(View.INVISIBLE);
    	//myAnimation_Translate1= AnimationUtils.loadAnimation(this,R.anim.my_translate_action1);
    	//ro.startAnimation(myAnimation_Translate1);
    }
    
    private long millis=300; 
    private RefreshHandler GNGhandler = new RefreshHandler();
   
    class RefreshHandler extends Handler{
		public void handleMessage(Message msg) {
			if (GNGView.getTouchEgg()){
				GNGView.setTouchEgg(false);
				happyRobot();
				
			}
   		 	showResult(GNGTouch.getGameFlag());
   		 		if (GNGTouch.getGuessFlag()){
   		 			this.sleep(millis);
   		 		}
        }

        public void sleep(long delayMillis) {
        	this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
	}
    
    protected static final int MENU_RESTART = Menu.FIRST;
    protected static final int MENU_HELP = Menu.FIRST+1;
    protected static final int MENU_ABOUT = Menu.FIRST+2;
    protected static final int MENU_QUIT = Menu.FIRST+3;
    
      @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, MENU_RESTART, 0, "重新开始").setIcon(R.drawable.refresh);
        menu.add(0, MENU_HELP, 0, "游戏指南").setIcon(R.drawable.help);
        menu.add(0, MENU_ABOUT, 0, "关于游戏").setIcon(R.drawable.info);
        menu.add(0, MENU_QUIT, 0, "不想玩了").setIcon(R.drawable.exit);
        return true;
    }
    
     @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
             case MENU_RESTART:
            	 Intent myintent = new Intent(getApplicationContext(),GuessNumber.class);
             	 startActivity(myintent);
             	 finish();
                 break;
             case MENU_HELP:
            	 gngDialog.setTitle(getString(R.string.help));
            	 dialogContent.setText(getString(R.string.help_content));
            	 dialogImage.setImageResource(R.drawable.dialog);
            	 gngDialog.show();
                 break;
             case MENU_ABOUT:
                 gngDialog.setTitle(getString(R.string.about));
                 dialogContent.setText(getString(R.string.about_content));
                 dialogImage.setImageResource(R.drawable.dialog);
            	 gngDialog.show();
                 break;
             case MENU_QUIT:
            	 finish();
            	 break;
           }
        return true;
     }
     
     
    
    
}