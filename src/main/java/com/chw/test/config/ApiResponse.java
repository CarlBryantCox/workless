package com.chw.test.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 服务接口有响应  统一规范响应服务接口信息
 * @author:chenwenbiao
 * @createTime:2019/10/22 16:19
 * @version：1.0
 **/
@Data
@Slf4j
public class ApiResponse {

	private Integer code;
	private String msg;
	private Object data;

	public ApiResponse() {
		this.code=0;
		this.msg="ok";
		this.data="success";
	}

	public ApiResponse(Integer code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public ApiResponse(Object data) {
		this.code = 0;
		this.msg = "ok";
		this.data = data;
	}

	public static ApiResponse getFailResponse(String msg){
		return new ApiResponse(1,msg,"failure");
	}

	@Override
	public String toString() {
		return "ResponseEntity [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

}
