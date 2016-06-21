package me.chanjar.weixin.common.bean.result;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
		String jsonContext  = readFile(WxErrorMsgUtil.class.getResource("").getPath()+"/error.json");
		System.out.println(jsonContext);
		List<WxErrMsg> list = gson.fromJson(jsonContext, new TypeToken<List<WxErrMsg>>(){}.getType());
		if(list!=null&&list.size()>0){
			for(WxErrMsg e:list){
				errorMsg.put(e.getKey(), e.getVal());
			}
		}
	}

	private static String readFile(String Path) {
		
		BufferedReader reader = null;
		String laststr = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(Path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
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
