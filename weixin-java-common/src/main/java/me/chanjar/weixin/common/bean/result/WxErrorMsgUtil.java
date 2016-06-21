package me.chanjar.weixin.common.bean.result;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.collections.Maps;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import me.chanjar.weixin.common.util.StringUtils;

public class WxErrorMsgUtil {
	private static final Map<Integer, String> errorMsg = new HashMap<>();
	private static final Gson gson = new Gson();
	static {
		initMap();
	}

	public static String getMsg(int code) {
		String msg = errorMsg.get(code);
		msg = StringUtils.isBlank(msg)?"":msg;
		return msg;
	}
	public static void initMap(){
		String jsonContext  = readFile();
		//System.out.println(jsonContext);
		List<WxErrMsg> list = gson.fromJson(jsonContext, new TypeToken<List<WxErrMsg>>(){}.getType());
		if(list!=null&&list.size()>0){
			for(WxErrMsg e:list){
				errorMsg.put(e.getKey(), e.getVal());
			}
		}
	}

	private static String readFile() {
		
		BufferedReader reader = null;
		String laststr = "";
		try {
			InputStream is=WxErrorMsgUtil.class.getClass().getResourceAsStream("/me/chanjar/weixin/common/bean/result/error.json");   
	        BufferedReader br=new BufferedReader(new InputStreamReader(is));  
			reader = new BufferedReader(br);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}
}
