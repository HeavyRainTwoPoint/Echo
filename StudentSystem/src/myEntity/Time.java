package myEntity;

import java.util.Calendar;

public class Time {
	//�����ĸ������ֱ����ꡢ�¡��ա�ʱ����
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	//����һ��date����
	public Time()
	{
		Calendar date = Calendar.getInstance();
		year = date.get(Calendar.YEAR);
		month = date.get(Calendar.MONTH)+1;
		day = date.get(Calendar.DATE);
		hour = date.get(Calendar.HOUR_OF_DAY);
		minute = date.get(Calendar.MINUTE);
	}
	public String toString()
	{
		return year+"/"+month+"/"+day+"  "+hour+":"+minute;
	}
}
