package com.fxmms.common.ro;

public class ControllerResult {

	public final static char SUCCESS = '1';
	public final static char ERROR = '2';
	public final static String SUCCESS_STR = "success";
	public final static String ERROR_STR = "error";
	
	private String result;
	private String msg;
	private int total;
	private Object rows;

	public static ControllerResult valueOf(char result) {
		switch (result) {
		case SUCCESS:
			return new ControllerResult(SUCCESS_STR);
		case ERROR:
			return new ControllerResult(ERROR_STR);
		default:
			return null;
		}
	}

	public static ControllerResult valueOf(char result, String msg) {
		ControllerResult r = ControllerResult.valueOf(result);
		if (r == null) {
			return null;
		}
		r.setMsg(msg);
		return r;
	}

	public static ControllerResult valueOf(char result, String msg,
			Object rows) {
		ControllerResult r = ControllerResult.valueOf(result, msg);
		if (r == null) {
			return null;
		}		
		r.setRows(rows);
		return r;
	}
	public static ControllerResult valueOf(char result, String msg,
			Object rows,int total) {
		ControllerResult r = ControllerResult.valueOf(result, msg, rows);
		if (r == null) {
			return null;
		}		
		r.setTotal(total);
		return r;
	}

	ControllerResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}	

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
