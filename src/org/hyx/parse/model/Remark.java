package org.hyx.parse.model;
import java.sql.Date;


public class Remark {

	private int id;
	private int cvId;
	private int type;
	private int cvStatus;
	private int creatorId;
	private int flag;
	private String content;
	private Date  createTime;
	private Date  updateTime;
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCvId() {
		return cvId;
	}
	public void setCvId(int cvId) {
		this.cvId = cvId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getCvStatus() {
		return cvStatus;
	}
	public void setCvStatus(int cvStatus) {
		this.cvStatus = cvStatus;
	}
	public int getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
