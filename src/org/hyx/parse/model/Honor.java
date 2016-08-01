package org.hyx.parse.model;

public class Honor {

	private String honorName;//奖项名称
	private String honorInfo;//奖项信息
	public String getHonorName() {
		return honorName;
	}
	public void setHonorName(String honorName) {
		this.honorName = honorName;
	}
	public String getHonorInfo() {
		return honorInfo;
	}
	public void setHonorInfo(String honorInfo) {
		this.honorInfo = honorInfo;
	}
	
	
	@Override
	public String toString() {
		return "Medal [honorName=" + honorName + ", honorInfo=" + honorInfo
         + "]";
	}
}
