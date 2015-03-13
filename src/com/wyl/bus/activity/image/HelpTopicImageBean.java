package com.wyl.bus.activity.image;

import java.io.Serializable;

/**
 * 
 * @author wyl
 * @date 2015年3月13日 上午11:02:44
 */
public class HelpTopicImageBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String imgUrl;
	private String imgInfo;
	
	public HelpTopicImageBean() {
		super();
	}
	public HelpTopicImageBean(String imgUrl, String imgInfo) {
		super();
		this.imgUrl = imgUrl;
		this.imgInfo = imgInfo;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getImgInfo() {
		return imgInfo;
	}
	public void setImgInfo(String imgInfo) {
		this.imgInfo = imgInfo;
	}
}
