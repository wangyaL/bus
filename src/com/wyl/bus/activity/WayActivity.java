package com.wyl.bus.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.wyl.bus.R;
import com.wyl.bus.common.MyCount;
import com.wyl.bus.util.PropertiesUtil;
import com.wyl.bus.util.StringUtil;

public class WayActivity extends Activity {
	private List<Map<String, Object>> mList;//用来存放要显示的数据
	private ListView mListView;
	/**
	 * SimpleAdapter是ListView 数据的一个容器， 
	 * 用来存放显示在ListView上的数据。
	 * 对 SimpleAdapter 的数据操作会直接影响到ListView的显示。
	 */
	private SimpleAdapter mListAdapter;
	
	/**
	 * 上行1，下行2
	 */
	private String state;
	/**
	 * 城市
	 */
	private String city;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_way);
		
		MyCount myCount = (MyCount) getApplication();
		state = myCount.getState();
		city = myCount.getCity();

		mListView = (ListView) findViewById(R.id.list_view_way);
		
		mList = new ArrayList<Map<String,Object>>();

		Properties pro = PropertiesUtil.getProperties(getApplicationContext(), city+"_bus.properties");
		String title = StringUtil.iso2Utf8((String) pro.get("way"));

		final String[] titles = title.split(",");
		
		for (int i=0; i<titles.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", titles[i].replace("night", "夜班车").replace("gaof", "高峰大战车")+"路");
			
			map.put("content", StringUtil.iso2Utf8((String) pro.get(titles[i]+"_content_"+state)));
			String time = StringUtil.iso2Utf8((String) pro.get(titles[i]+"_time_"+state));
			map.put("time", (time.length()>15) ? "" : time);
			
			mList.add(map);
		}
		
		mListAdapter = null;
		mListAdapter = new SimpleAdapter(this, mList, R.layout.list_row, 
				new String[]{"title", "content", "time"},
				new int[]{R.id.list_row_title, R.id.list_row_content, R.id.list_row_state});
		mListView.setAdapter(mListAdapter);
		
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(WayActivity.this, DetailActivity.class);
				intent.putExtra("wayNum", titles[arg2]);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.way, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		MyCount myCount = (MyCount) getApplication();
		switch (item.getItemId()) {
			case R.id.action_settings_city:
				String city = myCount.getCity();
				if("hzs".equals(city)){
					myCount.setCity("yqs");
					myCount.setHelloWorld("欢迎使用“乐清公交 一览”，点击下面的按钮查看公交列表");
				}else {
					myCount.setCity("hzs");
					myCount.setHelloWorld("欢迎使用“杭州公交 一览”，点击下面的按钮查看公交列表");
				}
				Intent toTop = new Intent(getApplicationContext(), CityActivity.class);
				toTop.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(toTop);
				break;
			case R.id.action_settings:
				String state = myCount.getState();
				if("1".equals(state)){
					myCount.setState("2");
				}else {
					myCount.setState("1");
				}
				startActivity(new Intent(WayActivity.this, WayActivity.class));
				finish();
				break;
			default:
				return super.onOptionsItemSelected(item);
		}
		return true;
	}
}
