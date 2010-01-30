package net.xsmile.game;

import net.xsmile.guessNumber.NewGuess;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

public class GuessView extends LinearLayout {

	private Context myContext;
	private float mTouchStartX;
    private float mTouchStartY;
    private float mTouchCurrX;
    private float mTouchCurrY;
    
    private NewGuess gngame;
    
    private boolean guessFlag=true;
    private String numberGuessed="";
    //private int guessleft=7;
    private String guessresult;
    private int gngFlag=-1;
    
    
	public GuessView(Context context) {
		super(context);
		myContext=context;
		initalize();
		// TODO Auto-generated constructor stub
	}

	public GuessView(Context context, AttributeSet attrs) {
		super(context, attrs);
		myContext=context;
		initalize();
		// TODO Auto-generated constructor stub
	}

	/*public GuessView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		myContext=context;
		initalize();
		// TODO Auto-generated constructor stub
	}*/

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//super.onDraw(canvas);

		paintGN(canvas);
	}
	
	private Bitmap getImage(int id){
		return BitmapFactory.decodeResource(myContext.getResources(), id);
	}
	
	@Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        
        
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            	
            	npInTouch=whoIsInTouch(x,y);
            	if (npInTouch>=0){
            		mTouchStartX = mTouchCurrX = x;
                    mTouchStartY = mTouchCurrY = y;
                    
            	}
                break;
            case MotionEvent.ACTION_MOVE:
            	//npInTouch=whoIsInTouch(x,y);
            	//Toast.makeText(myContext, "dd"+npInTouch, Toast.LENGTH_SHORT).show();
            	if (npInTouch>=0){
            		mTouchCurrX = x;
            		if (y<230){
            			mTouchCurrY=230;
            		}
            		else{
                    mTouchCurrY = y;
            		}
                    invalidate();            
            	}
            	
                break;
            case MotionEvent.ACTION_UP:
            	if (npInTouch>=0){
            		npX[npInTouch]=npX[npInTouch]+(mTouchCurrX - mTouchStartX);
            		npY[npInTouch]=npY[npInTouch]+(mTouchCurrY - mTouchStartY);
            		mTouchCurrX=mTouchCurrY=mTouchStartX=mTouchStartY=0;
            		if (isInPosition(x,y)){
                		invalidate();
                		checkGuess();
                	}
                	else{
                		checkPosition();
                	}
            		
            	}
            	
            	

            	//Toast.makeText(myContext, "dd"+inpX[0]+npX[0], Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
	
	public String getResult(){
		return guessresult;
	}
	
	public int getTimeLeft(){
		return gngame.getGuessTimeLeft();
	}
	
	public int getGameFlag(){
		return gngFlag;
	}
	
	public boolean getGuessFlag(){
		return guessFlag;
	}

	
	/****************************
	 * Game Code
	 ***************************/
	Paint nPaint=new Paint();
	Paint bgPaint=new Paint();
	Paint tPaint=new Paint();
	Paint rPaint=new Paint();
	
	String ccjg;

	
	int numbers=10;
	
	int GNG_WIDTH;
	int GNG_HIGHT;
	
	Bitmap[] np;
	
	float[] inpX=new float[numbers];
	float[] inpY=new float[numbers];
	
	float[] npX=new float[numbers];
	float[] npY=new float[numbers];
	//Bitmap[] npp;
	Bitmap nppo;
	float[] nppX=new float[4];
	float[] nppY=new float[4];
	int nppin[]=new int[]{-1,-1,-1,-1};
	
	Bitmap robot;
	float robotX;
	float robotY;
	
	int npWidth;
	int npHight;
	
	int npInTouch=-1;
	
	String toastText;
	Toast myToast;
	
	private void initalize(){
		setFocusable(true);
		setFocusableInTouchMode(true);
		gngame=new NewGuess(7);
		
		GNG_WIDTH=320;
		GNG_HIGHT=480;
		
		//robot=getImage(R.drawable.robot);
		//bgPaint.setColor(getResources().getColor(R.color.guessnumber_background));
		np=new Bitmap[]{getImage(R.drawable.np_0),getImage(R.drawable.np_1),getImage(R.drawable.np_2),
						getImage(R.drawable.np_3),getImage(R.drawable.np_4),getImage(R.drawable.np_5),
						getImage(R.drawable.np_6),getImage(R.drawable.np_7),getImage(R.drawable.np_8),getImage(R.drawable.np_9)};
		//npp=new Bitmap[]{getImage(R.drawable.npp),getImage(R.drawable.npp),getImage(R.drawable.npp),getImage(R.drawable.npp)};
		nppo=getImage(R.drawable.nppo);
		npWidth=getImage(R.drawable.np_1).getWidth();
		npHight=getImage(R.drawable.np_1).getHeight();
		ccjg=myContext.getString(R.string.rstring);
		//original position
		inpX[0]=30;
		inpY[0]=350;
		inpX[5]=30;
		inpY[5]=350+npHight+15;
		
		npX[0]=30;
		npY[0]=350;
		npX[5]=30;
		npY[5]=350+npHight+15;
		
		nppX[0]=30;
		nppY[0]=280;
		
		robotX=0;
		robotY=0;
		for (int n=1;n<4;n++){
			nppX[n]=n*50+n*20+30;
			nppY[n]=nppY[0];
		}
		
		for (int n=1;n<5;n++){
			npX[n]=n*npWidth+n*15+30;
			npY[n]=npY[0];
		}
		for (int n=6;n<10;n++){
			npX[n]=(n-5)*npWidth+(n-5)*15+30;
			npY[n]=npY[5];
		}
		
		for (int n=1;n<5;n++){
			inpX[n]=n*npWidth+n*15+30;
			inpY[n]=inpY[0];
		}
		for (int n=6;n<10;n++){
			inpX[n]=(n-5)*npWidth+(n-5)*15+30;
			inpY[n]=inpY[5];
		}
		
		//npX[1]=8+npWidth;
		//npY[1]=230;
		
	}
	
	private void paintGN(Canvas c){
		//c.drawRect(0, 0, GNG_WIDTH, GNG_HIGHT, bgPaint);
		//c.drawBitmap(robot, robotX, robotY, rPaint);
		
		for (int n=0;n<4;n++){
			c.drawBitmap(nppo, nppX[n], nppY[n], nPaint);
		}
		
		
		
		
		boolean npIsInTouch=false;
		for (int n=0;n<numbers;n++){
			if (n==npInTouch){
				npIsInTouch=true;
				continue;
			}
			c.drawBitmap(np[n], npX[n], npY[n], nPaint);
		}
		if(npIsInTouch){
			c.drawBitmap(np[npInTouch], npX[npInTouch]+(mTouchCurrX - mTouchStartX), npY[npInTouch]+(mTouchCurrY - mTouchStartY), nPaint);
		}
	}
	
	private int whoIsInTouch(float tx,float ty){
		int inTouch=-1;
		for (int n=0;n<numbers;n++){
			if ( tx >= npX[n] && tx <= npX[n] + npWidth
				&& ty >= npY[n] && ty <= npY[n] + npHight){
					inTouch=n;	
					break;
				}
		}
		return inTouch;
		
	}
	
	private boolean isInPosition(float tx,float ty){
		boolean flag=false;
		for (int n=0;n<4;n++){
			if ( tx >= nppX[n] && tx <= nppX[n] + 50
				&& ty >= nppY[n] && ty <= nppY[n] + 50){
				if (nppin[n]!=-1){
					npX[nppin[n]]=inpX[nppin[n]];
					npY[nppin[n]]=inpY[nppin[n]];
				}
				npX[npInTouch]=nppX[n]+5;
        		npY[npInTouch]=nppY[n]+5;
        		checkPosition();
        		nppin[n]=npInTouch;
        		flag=true;
        		break;
			}
		}
		return flag;
	}
	
	private void checkPosition(){
		for (int n=0;n<4;n++){
			if (nppin[n]==npInTouch){
				nppin[n]=-1;
				break;
			}
		}
	}
	
	private void checkGuess(){
		if (guessFlag){
		int n;
		for (n=0;n<4;n++){
			if (nppin[n]==-1){
				numberGuessed="";
				break;
			}
			else{
				numberGuessed=numberGuessed+nppin[n];
			}
		}
		if (n==4){
			gngFlag=gngame.NumberCompare(numberGuessed);
			numberGuessed="";
			guessresult=gngame.getResults().toString();
			showToast(gngFlag);
		}
		}
	}
	
	 private void showToast(int flag){
	    	switch (flag){
	    	case 0:
	    		guessFlag=false;
	    		toastText=myContext.getString(R.string.fail)+gngame.getNewNumber();
	    		myToast = Toast.makeText(myContext, toastText, Toast.LENGTH_LONG);
	    		myToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
	    		myToast.show();
	    		break;
	    	case 1:
	    		guessFlag=false;
	    		toastText=myContext.getString(R.string.success);
	    		myToast = Toast.makeText(myContext, toastText, Toast.LENGTH_LONG);
	    		myToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
	    		myToast.show();	    		
	    		break;
	    	case 2:
	    		  
	    		break;
	    	default:
	    		break;
	    	}
	    }

}
