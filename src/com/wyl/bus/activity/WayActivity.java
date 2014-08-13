package com.wyl.bus.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Shader.TileMode;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.wyl.bus.R;
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
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_way);
		
		mListView = (ListView) findViewById(R.id.list_view_way);
		
		mList = new ArrayList<Map<String,Object>>();

		Properties pro = PropertiesUtil.getProperties(getApplicationContext(), "bus.properties");
		String title = StringUtil.iso2Utf8((String) pro.get("way"));

		final String[] titles = title.split(",");
		
		for (int i=0; i<titles.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", titles[i]+"路");
			
			map.put("content", StringUtil.iso2Utf8((String) pro.get(titles[i]+"_content_1")));
			map.put("time", StringUtil.iso2Utf8((String) pro.get(titles[i]+"_time_1")));
			
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

}
