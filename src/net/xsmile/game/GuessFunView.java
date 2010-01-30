package net.xsmile.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class GuessFunView extends LinearLayout {

	private Context myContext;
	private float mTouchStartX;
    private float mTouchStartY;
    private float mTouchCurrX;
    private float mTouchCurrY;
    
    private int[] touchPP={0,0,0,0};
    private boolean touchEgg=false;
   
    
	public GuessFunView(Context context) {
		super(context);
		myContext=context;
		initalize();
		// TODO Auto-generated constructor stub
	}

	public GuessFunView(Context context, AttributeSet attrs) {
		super(context, attrs);
		myContext=context;
		initalize();
		// TODO Auto-generated constructor stub
	}

	

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//super.onDraw(canvas);

		paintGN(canvas);
	}
	
	public boolean getTouchEgg(){
		return touchEgg;
	}
	
	public void setTouchEgg(boolean flag){
		touchEgg=flag;
		for (int n=0;n<4;n++){
			touchPP[n]=0;
			}
		
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
            		if (y<20){
            			mTouchCurrY=20;
            		}
            		else if(y>155){
                    mTouchCurrY = 155;
            		}else{
            			mTouchCurrY = y;
            		}
            		invalidate();
            	}
            	
                break;
            case MotionEvent.ACTION_UP:
            	if (npInTouch>=0){
            		
            		ppX[npInTouch]=ppX[npInTouch]+(mTouchCurrX - mTouchStartX);
            		ppY[npInTouch]=ppY[npInTouch]+(mTouchCurrY - mTouchStartY);
            		mTouchCurrX=mTouchCurrY=mTouchStartX=mTouchStartY=0;
            		
                	invalidate();
                	gngEgg(npInTouch);
            		
            	}
            	
            	

            	//Toast.makeText(myContext, "dd"+inpX[0]+npX[0], Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
	
	

	
	/****************************
	 * Game Code
	 ***************************/
	Paint nPaint=new Paint();
	
	
	

	
	int numbers=4;
	
	//int GNG_WIDTH;
	//int GNG_HEIGHT;
	
	Bitmap[] pp;
	
	float[] ppWidth=new float[numbers];
	float[] ppHeight=new float[numbers];
	
	float[] ppX=new float[numbers];
	float[] ppY=new float[numbers];
	
	int npInTouch=-1;
	
	
	
	private void initalize(){
		setFocusable(true);
		setFocusableInTouchMode(true);
		
		
		//GNG_WIDTH=getWidth();
		//GNG_HEIGHT=getHeight();
		
		//robot=getImage(R.drawable.robot);
		//bgPaint.setColor(getResources().getColor(R.color.guessnumber_background));
		pp=new Bitmap[]{getImage(R.drawable.pp1),getImage(R.drawable.pp2),getImage(R.drawable.pp3),getImage(R.drawable.pp4)};
		for(int n=0;n<numbers;n++){
			ppWidth[n]=pp[n].getWidth();
			ppHeight[n]=pp[n].getHeight();
		}
		
		//original position
		
		
		ppX[0]=30;
		ppY[0]=35;
		ppX[1]=130;
		ppY[1]=135;
		ppX[2]=230;
		ppY[2]=153;
		ppX[3]=280;
		ppY[3]=70;
		
		
		
	}
	
	private void paintGN(Canvas c){
		//c.drawRect(0, 0, GNG_WIDTH, GNG_HIGHT, bgPaint);
		//c.drawBitmap(robot, robotX, robotY, rPaint);
		
		
		
		
		
		
		boolean npIsInTouch=false;
		for (int n=0;n<numbers;n++){
			if (n==npInTouch){
				npIsInTouch=true;
				continue;
			}
			c.drawBitmap(pp[n], ppX[n], ppY[n], nPaint);
		}
		if(npIsInTouch){
			c.drawBitmap(pp[npInTouch], ppX[npInTouch]+(mTouchCurrX - mTouchStartX), ppY[npInTouch]+(mTouchCurrY - mTouchStartY), nPaint);
		}
		
		
	}
	
	private int whoIsInTouch(float tx,float ty){
		int inTouch=-1;
		for (int n=0;n<numbers;n++){
			if ( tx >= ppX[n] && tx <= ppX[n] + ppWidth[n]
				&& ty >= ppY[n] && ty <= ppY[n] + ppHeight[n]){
					inTouch=n;	
					break;
				}
		}
		return inTouch;
		
	}
	
	private void gngEgg(int touch){
		int n;
		switch (touch){
    	case 0:
    		touchPP[0]=1;
    		break;
    	case 1:
    		touchPP[1]=1;
    		break;
    	case 2:
    		touchPP[2]=1;
    		break;
    	case 3:
    		touchPP[3]=1;
    		break;
    	}
		for (n=0;n<4;n++){
			if (touchPP[n]==0){
				break;
			}
		}
		if(n==4){
			touchEgg=true;
		}
	}
	
	

}
