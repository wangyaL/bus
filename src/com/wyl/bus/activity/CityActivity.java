package com.wyl.bus.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cn.sharesdk.demo.CustomShareFieldsPage;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.tencent.weibo.TencentWeibo;

import com.wyl.bus.R;
import com.wyl.bus.common.MyCount;

public class CityActivity extends Activity implements OnClickListener{
//	private TextView city_text;
	private Button btn_to_way, btn, btn_share, btn_share2;
	private TextView hello_text;
	private TextView hello_text_title;
	private String helloWorld;
	
	private boolean shareFromQQLogin = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city);
		
//		city_text = (TextView) findViewById(R.id.city_text);
		hello_text = (TextView) findViewById(R.id.hello_world);
		hello_text_title = (TextView) findViewById(R.id.hello_world_title);
		btn_to_way = (Button) findViewById(R.id.btn_to_way);
		btn = (Button) findViewById(R.id.btn);
		btn_share = (Button) findViewById(R.id.btn_share);
		btn_share2 = (Button) findViewById(R.id.btn_share2);
		
		MyCount myCount = (MyCount) getApplication();
		helloWorld = myCount.getHelloWorld();
		hello_text.setText(helloWorld);
		
		btn_to_way.setOnClickListener(this);
		btn.setOnClickListener(this);
		btn_share.setOnClickListener(this);
		btn_share2.setOnClickListener(this);
		
		String str = myCount.getHelloWorld().substring(5, 12);
		hello_text_title.setText(str);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_to_way:
			startActivity(new Intent(CityActivity.this, WayActivity.class));
			break;
		case R.id.btn:
			startActivity(new Intent(CityActivity.this, MainActivity.class));
			break;
		case R.id.btn_share:
			showShare();
			break;
		case R.id.btn_share2:
//			showShare();
			// 图文分享
			showShare(false, null, false);
			break;

		default:
			{
				// 分享到具体的平台
				Object tag = v.getTag();
				if (tag != null) {
					final String platformName = ((Platform) tag).getName();
					//QQ,QZone授权登录后发微博
					if(TencentWeibo.NAME.equals(platformName)){
						new AlertDialog.Builder(getContext())
						.setMessage(R.string.qq_share_way)
						.setPositiveButton(R.string.qq_share_from_qqlogin, new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								shareFromQQLogin = true;
								try
								{
									getContext().getPackageManager().getPackageInfo("com.qzone", 0);
									showShare(false, QZone.NAME, false);
								} catch (PackageManager.NameNotFoundException e)
								{
									showShare(false, QQ.NAME, false);
								}
							}
						})
						.setNegativeButton(R.string.qq_share_from_tlogin, new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								shareFromQQLogin = false;
								showShare(false, platformName, false);
							}
						})
						.setIcon(android.R.drawable.ic_dialog_alert)
						.show();
					}else{
						showShare(false, platformName, false);
					}
				}
			}
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.city, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch (item.getItemId()) {
			case R.id.action_settings_city:
				MyCount myCount = (MyCount) getApplication();
				String city = myCount.getCity();
				if("hzs".equals(city)){
					myCount.setCity("yqs");
					myCount.setHelloWorld("欢迎使用“乐清公交 一览”，点击下面的按钮查看公交列表");
				}else {
					myCount.setCity("hzs");
					myCount.setHelloWorld("欢迎使用“杭州公交 一览”，点击下面的按钮查看公交列表");
				}
				startActivity(new Intent(CityActivity.this, CityActivity.class));
				finish();
				break;
			default:
				return super.onOptionsItemSelected(item);
		}
		return true;
	}
	private void showShare() {
		 ShareSDK.initSDK(this);
		 OnekeyShare oks = new OnekeyShare();
		 //关闭sso授权
		 oks.disableSSOWhenAuthorize(); 

		// 分享时Notification的图标和文字
		 oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
		 // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		 oks.setTitle(getString(R.string.share));
		 // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		 oks.setTitleUrl("http://sharesdk.cn");
		 // text是分享文本，所有平台都需要这个字段
		 oks.setText("我是分享文本");
		 // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		 oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
		 // url仅在微信（包括好友和朋友圈）中使用
		 oks.setUrl("http://sharesdk.cn");
		 // comment是我对这条分享的评论，仅在人人网和QQ空间使用
		 oks.setComment("我是测试评论文本");
		 // site是分享此内容的网站名称，仅在QQ空间使用
		 oks.setSite(getString(R.string.app_name));
		 // siteUrl是分享此内容的网站地址，仅在QQ空间使用
		 oks.setSiteUrl("http://sharesdk.cn");

		// 启动分享GUI
		 oks.show(this);
	}
	// 使用快捷分享完成分享（请务必仔细阅读位于SDK解压目录下Docs文件夹中OnekeyShare类的JavaDoc）
	/**ShareSDK集成方法有两种</br>
	 * 1、第一种是引用方式，例如引用onekeyshare项目，onekeyshare项目再引用mainlibs库</br>
	 * 2、第二种是把onekeyshare和mainlibs集成到项目中，本例子就是用第二种方式</br>
	 * 请看“ShareSDK 使用说明文档”，SDK下载目录中 </br>
	 * 或者看网络集成文档 http://wiki.mob.com/Android_%E5%BF%AB%E9%80%9F%E9%9B%86%E6%88%90%E6%8C%87%E5%8D%97
	 * 3、混淆时，把sample或者本例子的混淆代码copy过去，在proguard-project.txt文件中
	 *
	 *
	 * 平台配置信息有三种方式：
	 * 1、在我们后台配置各个微博平台的key
	 * 2、在代码中配置各个微博平台的key，http://mob.com/androidDoc/cn/sharesdk/framework/ShareSDK.html
	 * 3、在配置文件中配置，本例子里面的assets/ShareSDK.conf,
	 */
	private void showShare(boolean silent, String platform, boolean captureView) {
		Context context = getContext();
		final OnekeyShare oks = new OnekeyShare();

		oks.setNotification(R.drawable.ic_launcher, context.getString(R.string.app_name));
		//oks.setAddress("12345678901");
		oks.setTitle(CustomShareFieldsPage.getString("title", context.getString(R.string.evenote_title)));
		oks.setTitleUrl(CustomShareFieldsPage.getString("titleUrl", "http://mob.com"));
		String customText = CustomShareFieldsPage.getString( "text", null);
		
		oks.setText(customText);
//		if (customText != null) {
//			oks.setText(customText);
//		} else if (MainActivity.TEST_TEXT != null && MainActivity.TEST_TEXT.containsKey(0)) {
//			oks.setText(MainActivity.TEST_TEXT.get(0));
//		} else {
//			oks.setText(context.getString(R.string.share_content));
//		}

		if (captureView) {
			oks.setViewToShare(getPage());
		} else {
			oks.setImagePath(CustomShareFieldsPage.getString("imagePath", ""));
			oks.setImageUrl(CustomShareFieldsPage.getString("imageUrl", ""));
		//	oks.setImageArray(new String[]{MainActivity.TEST_IMAGE, MainActivity.TEST_IMAGE_URL});
		}

		oks.setUrl(CustomShareFieldsPage.getString("url", "http://www.mob.com"));
		oks.setFilePath(CustomShareFieldsPage.getString("filePath", ""));
		oks.setComment(CustomShareFieldsPage.getString("comment", context.getString(R.string.share)));
		oks.setSite(CustomShareFieldsPage.getString("site", context.getString(R.string.app_name)));
		oks.setSiteUrl(CustomShareFieldsPage.getString("siteUrl", "http://mob.com"));
		oks.setVenueName(CustomShareFieldsPage.getString("venueName", "ShareSDK"));
		oks.setVenueDescription(CustomShareFieldsPage.getString("venueDescription", "This is a beautiful place!"));
		oks.setLatitude(23.056081f);
		oks.setLongitude(113.385708f);
		oks.setSilent(silent);
		oks.setShareFromQQAuthSupport(shareFromQQLogin);
		String theme = CustomShareFieldsPage.getString("theme", "classic");
		if(OnekeyShareTheme.SKYBLUE.toString().toLowerCase().equals(theme)){
			oks.setTheme(OnekeyShareTheme.SKYBLUE);
		}else{
			oks.setTheme(OnekeyShareTheme.CLASSIC);
		}

		if (platform != null) {
			oks.setPlatform(platform);
		}


		// 令编辑页面显示为Dialog模式
		oks.setDialogMode();

		// 在自动授权时可以禁用SSO方式
		//if(!CustomShareFieldsPage.getBoolean("enableSSO", true))
			oks.disableSSOWhenAuthorize();

		// 去除注释，则快捷分享的操作结果将通过OneKeyShareCallback回调
		//oks.setCallback(new OneKeyShareCallback());

		// 去自定义不同平台的字段内容
		//oks.setShareContentCustomizeCallback(new ShareContentCustomizeDemo());

		// 去除注释，演示在九宫格设置自定义的图标
		Bitmap enableLogo = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		Bitmap disableLogo = BitmapFactory.decodeResource(getResources(), R.drawable.sharesdk_unchecked);
		String label = getResources().getString(R.string.app_name);
		OnClickListener listener = new OnClickListener() {
			public void onClick(View v) {
				String text = "Customer Logo -- ShareSDK " + ShareSDK.getSDKVersionName();
				Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
			}
		};
		oks.setCustomerLogo(enableLogo, disableLogo, label, listener);

		// 去除注释，则快捷分享九宫格中将隐藏新浪微博和腾讯微博
//		oks.addHiddenPlatform(SinaWeibo.NAME);
//		oks.addHiddenPlatform(TencentWeibo.NAME);

		// 为EditPage设置一个背景的View
		oks.setEditPageBackground(getPage());

		//设置kakaoTalk分享链接时，点击分享信息时，如果应用不存在，跳转到应用的下载地址
		oks.setInstallUrl("http://www.mob.com");
		//设置kakaoTalk分享链接时，点击分享信息时，如果应用存在，打开相应的app
		oks.setExecuteUrl("kakaoTalkTest://starActivity");

		oks.show(context);
	}
	private Context getContext(){
		return this;
	}
	private View getPage(){
		View view = new View(this);
		return view;
	}


}
