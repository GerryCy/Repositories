package cn.test.ssm.utils;

import java.util.HashMap;
import java.util.Map;

public class Message {

    //用于返回状态信息
    private int state;

    //用于返回提示消息
    private String msg;

    //用于返回浏览器的数据
    private Map<String, Object> data = new HashMap<String, Object>();

    //返回状态的方法
    public static Message success() {
        Message msgSucc = new Message();
        msgSucc.setState(200);
        msgSucc.setMsg("Success!");
        return msgSucc;
    }

    public static Message failed() {
        Message msgFailed = new Message();
        msgFailed.setState(500);
        msgFailed.setMsg("Failed!");
        return msgFailed;
    }

    //添加数据的方法
    public Message updateData(String key, Object value) {
        this.getData().put(key, value);

        return this;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
