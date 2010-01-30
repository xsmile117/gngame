package net.xsmile.guessNumber;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**  猜数字
 * 
 * @author xsmile   2007-11-01
 *
 *
 */

public class NewGuess implements NumberGuess  {
	private int[] newNumber=new int[4];
	private int guessTime;
	private int guessedTime;
	private int guessTimeLeft;
	private String results;

	
	
	/* （非 Javadoc）
	 * @see com.xsmile.guessNumber.NumberGuess#getGuessTimeLeft()
	 */
	public int getGuessTimeLeft(){
		guessTimeLeft=guessTime-guessedTime;
		return guessTimeLeft;
	}
	
	public NewGuess(){
		guessTime=12;
		guessedTime=0;
		this.NewNumber();
		results="";
		
	}
	
	public NewGuess(int i){
		guessTime=i;
		guessedTime=0;
		this.NewNumber();
		results="";
	}
	
	
	/* （非 Javadoc）
	 * @see com.xsmile.guessNumber.NumberGuess#getNewNumber()
	 */
	public String getNewNumber() {
		String newNum=Integer.toString(newNumber[0])+
						Integer.toString(newNumber[1])+
						Integer.toString(newNumber[2])+
						Integer.toString(newNumber[3]);
		return newNum;
	}
	
	
	/* （非 Javadoc）
	 * @see com.xsmile.guessNumber.NumberGuess#getGuessTime()
	 */
	public int getGuessTime() {
		return guessTime;
	}
	
	
	/* （非 Javadoc）
	 * @see com.xsmile.guessNumber.NumberGuess#getGuessedTime()
	 */
	public int getGuessedTime() {
		return guessedTime;
	}
	
	/*
	public String getResults() {
		Iterator it=results.iterator();
		String Sresults="";
		while(it.hasNext()){
			Sresults += it.next()+"\n";
		}
		
		return Sresults;
	}
    */
	
	
	/* （非 Javadoc）
	 * @see com.xsmile.guessNumber.NumberGuess#getResults()
	 */
	public String getResults(){
		return results;
	}
	
	private void NewNumber(){
		Set tempNum=new HashSet();
		int i;
		for(i=1;i<5;i++){			
			Random rd=new Random();
			int temp=rd.nextInt(10);
			tempNum.add(Integer.toString(temp));
			if(tempNum.size()==i){			
			newNumber[i-1]=temp;
			continue;
			}
			i--;
		}
	}
	
	private int[] SNumToNumber(String number){
		int[] numbers=new int[4];
		for(int i=0;i<4;i++){
			String temp=number.substring(i, i+1);
			numbers[i]=Integer.parseInt(temp);
		}
		return numbers;
	}
	
	/* （非 Javadoc）
	 * @see com.xsmile.guessNumber.NumberGuess#NumberCompare(java.lang.String)
	 */
	public int NumberCompare(String number){

		if(checkNumber(number)){
			guessedTime++;
			return 1;
		}else if (guessedTime!=guessTime){
			guessedTime++;
		}
		if(checkTime()){
			return 2;
		}else{
			return 0;
		}
				
		
		
	}
	
	
	/* （非 Javadoc）
	 * @see com.xsmile.guessNumber.NumberGuess#isNumberValid(java.lang.String)
	 */
	public boolean isNumberValid(String number){
		Set temp=new HashSet();
		int[] gNumber=SNumToNumber(number);
		for(int i=0;i<4;i++){
		temp.add(Integer.toString(gNumber[i]));
		}
		while(temp.size()==4){
		return true;
		}
		return false;
			
	}
	
	private boolean checkTime(){
		if(guessedTime>guessTime-1){
			return false;
		}
		return true;
	}
	
	private boolean checkNumber(String number){
		int A=0,B=0;
		
		int[] guessNumber=SNumToNumber(number);
		for(int i=0;i<4;i++){
			if(guessNumber[i]==newNumber[i]){
				A++;
				continue;
			}
				for(int j=0;j<4;j++){
					if(guessNumber[i]==newNumber[j]){
						B++;
						break;
					}
				}
			
		}
		
		results=results+number + "--"+Integer.toString(A)+"A"+Integer.toString(B)+"B"+" ";
		
		if(A==4){
			return true;
		}
		else{
			return false;
		}
	}
}
