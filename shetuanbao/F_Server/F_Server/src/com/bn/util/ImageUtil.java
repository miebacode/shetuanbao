package com.bn.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtil 
{
	 DataInputStream din;
	 static DataOutputStream dout;
	 static FileInputStream fis;
	//��ȡ����ͼƬ
	public static byte[] getImage(String path)
	{								
		byte[] data=null;			                              //����ͼƬ��������								
		try
		{
			//����·������������
			BufferedInputStream in=new BufferedInputStream(new FileInputStream(path));
			//����һ���µĻ����������ָ����������СΪ1024Byte
			ByteArrayOutputStream out=new ByteArrayOutputStream(1024);
			byte[] temp=new byte[1024];		                      //������СΪ1024�ı�������
			int size=0;						                      //�����С����
			while((size=in.read(temp))!=-1)                       //�������ݶ�����д���������
			{	
				out.write(temp,0,size);				              //д���������
			}
			data=out.toByteArray();			                      //��ͼƬ��Ϣ�Ա���������ʽ��������ֵ��ͼƬ��������
			out.close();                                          //�ر������
			in.close();                                           //�ر�������
		}
		catch(Exception e)
		{
			e.printStackTrace();                                  //��ӡ�쳣��Ϣ
		}
		return data; 		                                      //���ر�������
	}
	//����ͼƬ
	public static void savePic(byte[] data,String path) throws IOException
	{
		File file= new File(path);                                   //�����ļ�
		FileOutputStream fos = new FileOutputStream(file);           //��Fileʵ�����������
		fos.write(data);                                             //��ʵ������д���ļ���
		fos.flush();                                                 //��ջ���������
		fos.close();                                                 //�ر��ļ���
	}
}
