package com.DS.Api.Impl;
import com.DS.Api.ReplyAPI;
public class ReplyAPI_impl implements ReplyAPI {
	//客户端连接的url例子：http://localhost:8080/api
	@Override
	public String testReply() {
		String replyData="远程调用数据成功！";
		return replyData;
	}

}
