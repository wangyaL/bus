package com.wyl.bus.common;

import android.app.Application;
/**
 * 全局变量，也可以变化的
 * @author wyl
 *
 */
public class MyCount extends Application{
	private String helloWorld="欢迎使用“杭州公交 一览”，点击下面的按钮查看公交列表";
	/**
	 * 选择城市，与配置文件名对应，如hzs_bus.properties则是hzs
	 */
	private String city = "hzs";
	/**
	 * 选择上下行：上行1，下行2
	 */
	private String state = "1";

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getHelloWorld() {
		return helloWorld;
	}
	public void setHelloWorld(String helloWorld) {
		this.helloWorld = helloWorld;
	}
}
