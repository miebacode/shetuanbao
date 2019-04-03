package com.bn.user_myself;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bn.util.Constant;
import com.bn.util.F_GetBitmap;
import com.bn.util.FontManager;
import com.bn.util.NetInfoUtil;
import com.bn.util.RoundImageView;
import com.example.Team_Android.MainMyselfActivity;
import com.example.chat.R;

public class user_gerenziliao extends Activity {
	String id = null;
	String photopath = "999";
	String path;
	ProgressDialog pd;
	TextView fanhui = null;
	TextView baocun = null;
	TextView username = null;
	EditText name = null;
	EditText sex = null;
	EditText e_mail = null;
	EditText phone = null;
	private EditText join=null;
	byte[] temp;
	Bitmap bit;
	RoundImageView touxiang = null;
	RelativeLayout changephoto;
	private List<String[]> usermessage = null;
	String message[][] = null;
	String mes;
	String zhanghao, xingming, xingbie, youxiang, thispen, dianhua;
	static Uri uri;
	Bitmap cameraBitmap;
	Bitmap bm = null;
	byte all_image[]=null;
	private EditText pen=null;
	private EditText thisxueyuan=null;
	private EditText thismajor=null;
	private String[] jointeam=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.geren_gerenziliao);
		pd = new ProgressDialog(this);
		pd.setMax(100);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);
		pd.setMessage("加载中...");
		photopath=Constant.userName;
		pen=(EditText)findViewById(R.id.myselfmain_pen_edit);
		FontManager.changeFonts(FontManager.getContentView(this), this);
		fanhui = (TextView) findViewById(R.id.myselftool_text1);
		thisxueyuan=(EditText)findViewById(R.id.myselfmain_xueyuan_edit);
		join=(EditText)findViewById(R.id.myselfmain_join_edit);
		thismajor=(EditText)findViewById(R.id.myselfmain_major_edit);
		fanhui.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (bit != null && !bit.isRecycled()) {
					bit.recycle();
					bit = null;
				}
				if (cameraBitmap != null && !cameraBitmap.isRecycled()) {
					cameraBitmap.recycle();
					cameraBitmap = null;
				}
				System.gc();
				finish();
			}
		});
		baocun = (TextView) findViewById(R.id.myselftool_text3);
		baocun.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                if(username.getText().equals(null)||name.getText().equals("")||sex.getHint().equals(null)||sex.getText().equals("")||e_mail.getText().equals("")||pen.getText().equals("")||phone.getText().equals(""))
                {
                	Toast.makeText(user_gerenziliao.this, "不可有空的选项",
							Toast.LENGTH_SHORT).show();
                }
                else {
                thread_set th = new thread_set();
				th.start();
				try {
					th.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Builder b = new AlertDialog.Builder(user_gerenziliao.this);
				b.setTitle("信息保存");
				b.setMessage("保存成功！");// 设置信息
				b.setPositiveButton// 为对话框设置按钮
						("确定", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								String picFilePath = path;
								BitmapFactory.Options options = new BitmapFactory.Options();
								options.inDither = false; /* 不进行图片抖动处理 */
								options.inPreferredConfig = null; /* 设置让解码器以最佳方式解码 */
								options.inSampleSize = 1; /* 图片长宽方向缩小倍数 */
								Bitmap bm = BitmapFactory.decodeFile(picFilePath, options);
								if(bm==null){
									MainMyselfActivity.photo.setImageBitmap(bit);
								}else{
									MainMyselfActivity.photo.setImageBitmap(bm);
								}
								finish();
							}
						});
				b.create().show();
			}}
		});
		username = (TextView) findViewById(R.id.myselfmain_username_edit);
		username.setOnClickListener(new View.OnClickListener() {
		    @Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		    	Toast.makeText(user_gerenziliao.this, "账号不可修改",
						Toast.LENGTH_LONG).show();
			}
		});
		thisxueyuan.setOnClickListener(new View.OnClickListener() {
		    @Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		    	Toast.makeText(user_gerenziliao.this, "学院不可修改",
						Toast.LENGTH_LONG).show();
			}
		});
		thismajor.setOnClickListener(new View.OnClickListener() {
		    @Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		    	Toast.makeText(user_gerenziliao.this, "专业不可修改",
						Toast.LENGTH_LONG).show();
			}
		});
		
		name = (EditText) findViewById(R.id.myselfmain_name_edit);
		sex = (EditText) findViewById(R.id.myselfmain_sex_edit);
		sex.setOnClickListener(new View.OnClickListener() {
		    @Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		    	Toast.makeText(user_gerenziliao.this, "性别不可修改",
						Toast.LENGTH_LONG).show();
			}
		});
		e_mail = (EditText) findViewById(R.id.myselfmain_e_mail_edit);
		phone = (EditText) findViewById(R.id.myselfmain_phone_edit);
		touxiang = (RoundImageView) findViewById(R.id.set_user_image);
		changephoto = (RelativeLayout) findViewById(R.id.myselfmain_1);
		changephoto.setOnClickListener(new View.OnClickListener() {
		    @Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.addCategory(Intent.CATEGORY_OPENABLE);
				intent.setType("image/*");
				startActivityForResult(intent, 0);
			}
		});
		thread th_l = new thread();
		th_l.start();
		try {
			th_l.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		FontManager.initTypeFace(this);
		FontManager.changeFonts(FontManager.getContentView(this), this);
	}
	private void crop(Uri uri) {
		// // 裁剪图片意图
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		//裁剪框的比例，1:1
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		//intent.putExtra("outputFormat", "PNG");// 图片格式
		intent.putExtra("noFaceDetection", true);// 取消人脸识别
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 1);
	}
	@SuppressWarnings("deprecation")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_OK) { // 此处的 RESULT_OK 是系统自定义得一个常量
			return;
		}
		@SuppressWarnings("unused")
		// 外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
		ContentResolver resolver = getContentResolver();
		// 此处的用于判断接收的Activity是不是你想要的那个 社团-- 0代表 拿图片路径 1表示设置通过剪切得到的头像
		if (requestCode == 0) {
			try {
				Uri originalUri = data.getData();
				crop(originalUri);// 获得图片的uri
				bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);
				String[] proj = { MediaColumns.DATA };
				// 好像是android多媒体数据库的封装接口，具体的看Android文档
				Cursor cursor = managedQuery(originalUri, proj, null, null,
						null);
				// 按我个人理解 这个是获得用户选择的图片的索引值ֵ
				int column_index = cursor
						.getColumnIndexOrThrow(MediaColumns.DATA);
				// 将光标移至开头 ，这个很重要，不小心很容易引起越界
				cursor.moveToFirst();
				// 最后根据索引值获取图片路径
				path = cursor.getString(column_index);
				File tempFile =new File( path.trim());
				photopath = tempFile.getName();
				photopath=Constant.userName;
                thread_insert hs=new thread_insert();
                hs.start();
                try{
                	hs.join();
                }catch(Exception e){
                	e.printStackTrace();
                }
			} catch (IOException e) {
			}
		}
		if (requestCode == 1) {
			cameraBitmap = (Bitmap) data.getExtras().get("data");
			super.onActivityResult(requestCode, resultCode, data);
			touxiang.setImageBitmap(cameraBitmap);
		}
	}
	class thread_insert extends Thread {
		@Override
		public void run() {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
			byte[] data1 = baos.toByteArray();
			NetInfoUtil.insertpic(data1,photopath+".png"); 
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			all_image = NetInfoUtil.getPicture(photopath+".png");
			F_GetBitmap.setInSDBitmap(all_image, photopath+".png");
		}
	}
	class thread extends Thread {
		@Override
		public void run() {
			jointeam=NetInfoUtil.getuserjointeam(Constant.userName);
			usermessage = NetInfoUtil.getusermessagebyid(Constant.userName);
			message = new String[usermessage.size()][usermessage.get(0).length];
			for (int i = 0; i < usermessage.size(); i++) {
				for (int j = 0; j < usermessage.get(i).length; j++) {
					message[i][j] = usermessage.get(i)[j];
				}
			}
			String data=" ";
			for(int i=0;i<jointeam.length;i++)
			{
				data+=jointeam[i]+" ";
			}
			join.setText(data);
			username.setText(message[0][1]);
			name.setHint(message[0][2]);
			sex.setHint(message[0][3]);			
			e_mail.setHint(message[0][4]);
			phone.setHint(message[0][5]);
			pen.setHint(message[0][6]);
			thisxueyuan.setHint(message[0][7]);
			thismajor.setHint(message[0][8]);
			String image = message[0][0];
			if (F_GetBitmap.isEmpty(image)) {
				temp = NetInfoUtil.getscPicture(image);
				F_GetBitmap.setInSDBitmap(temp, image);
				InputStream input = null;
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inSampleSize = 2;
				input = new ByteArrayInputStream(temp);
				@SuppressWarnings({ "unchecked", "rawtypes" })
				SoftReference softRef = new SoftReference(
						BitmapFactory.decodeStream(input, null, options));
				bit = (Bitmap) softRef.get();
			} else {
				bit = F_GetBitmap.getSDBitmap(image);// �õ�����BitMap���͵�ͼƬ����
				if (F_GetBitmap.bitmap != null
						&& !F_GetBitmap.bitmap.isRecycled()) {
					F_GetBitmap.bitmap = null;
				}
			}
			touxiang.setImageBitmap(bit);
		}
	}
	class thread_set extends Thread {
		@Override
		public void run() {
			zhanghao = username.getText().toString();
			mes = zhanghao;
			if (!name.getText().toString().equals("")) {
				xingming = name.getText().toString();
			} else {
				xingming = name.getHint().toString();
			}
			mes = mes + "<#>" + xingming;
			if (!sex.getText().toString().equals("")) {
				xingbie = sex.getText().toString();
			} else {
				xingbie = sex.getHint().toString();
			}
			mes = mes + "<#>" + xingbie;
			if (!e_mail.getText().toString().equals("")) {
				youxiang = e_mail.getText().toString();
			} else {
				youxiang = e_mail.getHint().toString();
			}
			mes = mes + "<#>" + youxiang;
			if (!phone.getText().toString().equals("")) {
				dianhua = phone.getText().toString();
			} else {
				dianhua = phone.getHint().toString();
			}
			mes = mes + "<#>" + dianhua;			
			if (!pen.getText().toString().equals("")) {
				thispen = pen.getText().toString();
			} else {
				thispen = pen.getHint().toString();
			}
			mes=mes+"<#>"+thispen;
			mes = mes + "<#>" + photopath;
			NetInfoUtil.setusermessagebyid(mes);
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (bit != null && !bit.isRecycled()) {
				bit.recycle();
				bit = null;
			}
			if (cameraBitmap != null && !cameraBitmap.isRecycled()) {
				cameraBitmap.recycle();
				cameraBitmap = null;
			}
			System.gc();
			finish();
		}
		return false;
	}
}