package com.bn.Server;

import java.net.*;

public class ServerThread extends Thread{
      ServerSocket ss;                                   //����ServerSocket
      @Override
      public void run()                                  //��дrun����
      {
    	  try{
    		  ss=new ServerSocket(10009);                 //���������������Ҽ���10006
    		  System.out.println("Listen on 10009....");  //��ʾ10006�Ѽ���
    		  while(true)
    		  {			 
    			  Socket sc=ss.accept();  			      //�ȴ��ͻ��� ����10006�˿�
    			  new ServerAgentThread(sc).start();      //�����µ��̲߳�����
    		  }
    	  }catch(Exception e)
    	  {
    		  e.printStackTrace();                        //��ӡ�쳣��Ϣ
    	  }
      }
     
      public static void main(String args[])
      {
    	  new ServerThread().start();                      //�������߳�
      }
}
