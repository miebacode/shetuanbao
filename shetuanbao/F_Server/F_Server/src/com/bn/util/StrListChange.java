package com.bn.util;

import java.util.ArrayList;
import java.util.List;

public class StrListChange
{
	//���ַ���ת����List
	public static List<String[]> StrToList(String info)
	{
		List<String[]> list=new ArrayList<String[]>();         //����һ��List�б�
		String[] s=info.split("\\|");                          //���ַ�����"|"Ϊ��ָ
		int num=0;                                             //�����С����
		for(String ss:s)                                       //��������
		{
			num=0;                                             //������
			String[] temp=ss.split("<#>");                     //���ַ�����"<#>"Ϊ��ָ
			String[] midd=new String[temp.length];             //������ʱ����
			for(String a:temp)                                 //��������
			{
				midd[num++]=a;	
			}
			list.add(midd);                                    //���ַ��������б�
		}
		return list;                                           //�����б�
	}
	
	//���ַ���ת�����ַ�����
	public static String[] StrToArray(String info)
	{
		int num=0;                                              //�����С����
		String[] first=info.split("\\|");                       //���ַ�����"|"Ϊ��ָ
		for(int i=0;i<first.length;i++)                         //�����ַ�������
		{
			String[] temp1=first[i].split("<#>");               //���ַ�����"<#>"Ϊ��ָ
			num+=temp1.length;
		}
		String[] temp2=new String[num];                         //������ʱ����
		num=0;                                                  //����
		for(String second:first)                                //��������
		{
			String[] temp3=second.split("<#>");                 //���ַ�����"<#>"Ϊ��ָ
			for(String third:temp3)                             //��������
			{
				temp2[num]=third;                               //����ʱ���鸳ֵ
				num++;
			}
		}
		return temp2;                                            //��������
	}
	
	//��Listת�����ַ���
	public static String ListToStr(List<String[]> list)
	{ 
		String mess="";                                          //��������
		List<String[]> ls=new ArrayList<String[]>();             //����list�б�
		ls=list;                                          
		for(int i=0;i<ls.size();i++)                             //�����б�
		{
			String[] ss=ls.get(i);
			for(String s:ss)                                     //��������
			{
				mess+=s+"<#>";                                   //���ַ�����"<#>"Ϊ��ָ
		    }
			mess+="|";                                           //���ַ�����"|"Ϊ��ָ
		}
		return mess;                                             //�����ַ���
	}
	
	//��Listת�����ַ��� ����׿�ˣ�
	public static String ListToStrAndroid(List<String[]> list)
	{
		if(list.isEmpty())
		{
			return "";
		}
		String mess="";
		List<String[]> ls=new ArrayList<String[]>();
		ls=list;
		for(int i=0;i<ls.size();i++)
		{
			String[] ss=ls.get(i);
			for(String s:ss)
			{
				mess+=s+"<#>";
		    }
			mess=mess.substring(0,mess.length()-3);
			mess+="|";
		}
		return mess.substring(0,mess.length()-1);
	}
	
	//�ų���ͬ������ �����ַ���
	public static String Streamline(String mess)
	{
		String[] str=mess.split("<#>");
		String info="";
		for(int i=0;i<str.length-1;i++)
		{
			String temp=str[i];
			String temp2=str[i+1];
			if(!temp.equals(temp2))
			{
				info+=temp+"<#>";
			}
			else
			{
				continue;
			}
		}
		return info+str[str.length-1];
	}
	
	//�ַ�����ĳ��ַ���
	public static String ArrayToStrAndroid(String[] arry)
	{
		String str="";
		for(String ss:arry)
		{
			if(ss!=null)
			{
				str+=ss+"|";
			}
		}
		return str;
	}
}
