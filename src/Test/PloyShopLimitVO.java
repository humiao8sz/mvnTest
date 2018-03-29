package Test;

import java.util.Date;

/**
 * 商城限购对象封装
 * @author Administrator
 *
 */
public class PloyShopLimitVO {
	private byte type;	//类型(1-针对单个玩家 2-针对全服  3-个人全服同时限制)
	private Date startTime;//开始时间
	private Date endTime;//结束时间
	private String buyLimit;//购买限制条件
	private int nowPrice;//当前价格
	private short personLimit;//个人限购数量
	private short globalLimit;//全服限购数量
	private String reward;//购买物品信息
	
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getBuyLimit() {
		return buyLimit;
	}
	public void setBuyLimit(String buyLimit) {
		this.buyLimit = buyLimit;
	}
	public int getNowPrice() {
		return nowPrice;
	}
	public void setNowPrice(int nowPrice) {
		this.nowPrice = nowPrice;
	}
	public short getPersonLimit() {
		return personLimit;
	}
	public void setPersonLimit(short personLimit) {
		this.personLimit = personLimit;
	}
	public short getGlobalLimit() {
		return globalLimit;
	}
	public void setGlobalLimit(short globalLimit) {
		this.globalLimit = globalLimit;
	}
	public String getReward() {
		return reward;
	}
	public void setReward(String reward) {
		this.reward = reward;
	}
	}
