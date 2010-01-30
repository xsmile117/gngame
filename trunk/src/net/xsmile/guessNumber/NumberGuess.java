package net.xsmile.guessNumber;

import java.util.List;


/**
 * The interface of NumberGuess
 * @author xsmile  2007-11-01
 *
 */
public interface NumberGuess {

	public abstract int getGuessTimeLeft();//����ʣ��²����
	
	public abstract String getNewNumber();//���ش�������
	
	public abstract int getGuessTime();//���ؿɲ²��ܴ���
	
	public abstract int getGuessedTime();//�����Ѳ²����

	public abstract String getResults();//���ز²�����
	
	
	/**
	 * 
	 * @param number
	 * @return 0��1��2������ֵ��
	 *          0��ʾ��Ϸ������
	 *          1��ʾ�²�ɹ���
	 *          2��ʾ���β²�ʧ�ܣ��ɼ����²�
	 */
	public abstract int NumberCompare(String number);

	public abstract boolean isNumberValid(String number);//�ж����������Ƿ�Ϸ��������ظ���

}