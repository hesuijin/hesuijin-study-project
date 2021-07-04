package com.example.demo.constant;

import com.alibaba.fastjson.JSONObject;

import java.text.MessageFormat;
import java.util.List;

/**
 * 后台接口数据传输模型
 * @author hzh 2018/2/4
 */
public class Result {
	/**
	 * 状态 10001 成功
	 */
	private int status;
	/**
	 * 消息内容
	 */
	private String msg;
	/**
	 * 返回数据
	 */
	private Object data;
	/**
	 * 数据总记录数
	 */
	private int totalCount;
	/**
	 * UTC时间
	 */
	private String time = null;

	public Result() {
		this.status = ResponseStatus.OK;
		this.time = TimeUtils.getUTCTime();
	}

	public static <T extends List> Result success(T data, Integer totalCount) {
		Result result = new Result();
		result.setData(data);
		result.setTotalCount(totalCount);
		return result;
	}

	public static <T> Result success(T data) {
		Result result = new Result();
		result.setData(data);
		return result;
	}

	public static Result fail(String msg) {
		Result result = new Result();
		result.setStatus(ResponseStatus.ERROR);
		result.setMsg(msg);
		return result;
	}

	public static Result fail(int status, String msg) {
		Result result = new Result();
		result.setStatus(status);
		result.setMsg(msg);
		return result;
	}

	public static Result success() {
		return new Result();
	}

	/**
	 * 错误返回
	 * @param status
	 * @param msg
	 * @param params
	 * @author hzh
	 */
	public void error (int status, String msg, String ... params) {
		this.status = status;
		if (null == params) {
			this.msg = msg;
		} else {
			this.msg = MessageFormat.format(msg, params);
		}
	}

	/**
	 * 错误返回
	 * @param status
	 * @param msg
	 * @author hzh
	 */
	public void error (int status, String msg) {
		error(status, msg, null);
	}

	public void put (String key, Object value) {
		if (null == this.data) {
			data = new JSONObject();
		}
		((JSONObject) this.data).put(key, value);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
