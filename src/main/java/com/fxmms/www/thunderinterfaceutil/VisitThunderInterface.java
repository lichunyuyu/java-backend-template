package com.fxmms.www.thunderinterfaceutil;

import com.fxmms.common.jniutil.GetDownloadIDUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark on 16/10/21.
 * @notice:  代码需要重构
 * @usahge： 请求迅雷提供接口
 */
public class VisitThunderInterface {
    /**
     * @param macStrList
     * @return
     * @uage 批量区间，单个录入静态方法
     */
    public static boolean addDownLoadId(List<String> macStrList) {
        boolean isSuccess = false;
        String randomStr = "578f5db6-cac8-4ec6-ae21-b33fffc44e23";
        String ckey = "1924F38DF4982A557D86785B29B0CB11";
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String securityKey = "E9AC45B450630411CE6D8BCE41FF6B33";
        String sign = MD5util.MD5(randomStr + timeStamp + securityKey);
        String initJsonParam = "{\"company\":\"982\",\"deviceType\":\"10\",\"license\":\"1609180001000005e000982l1ujfs3ya6ko62dlgmq\"," +
                "\"platform\":\"X0020\",\"snList\":[";
        int size = macStrList.size();
        int count = 1;
        String downLoadStr = "";
        for (String macStr : macStrList) {
            downLoadStr = GetDownloadIDUtil.getDownLoadId(macStr);
            if (count < size) {
                initJsonParam += "\"" + downLoadStr + "\",";
            } else {
                initJsonParam += "\"" + downLoadStr + "\"]}";
            }
            count++;
        }
        long timeSum = 0;
        System.out.println(initJsonParam + "--------");
        String requesrParamPrefixStr = "http://60.217.235.176/rope/1.0.0/syncDeviceId?ct=28";
        HttpClient httpClient = HttpClientBuilder.create().build();
        String result = "";
        try {
            HttpPost request = new HttpPost(requesrParamPrefixStr);
            StringEntity params = new StringEntity(initJsonParam);
            request.addHeader("content-type", "application/json");
            request.addHeader("XL-REQUEST-RANDOM", randomStr);
            request.addHeader("XL-REQUEST-TIMESTAMP", timeStamp);
            request.addHeader("XL-COMPANY-KEY", ckey);
            request.addHeader("XL-SIGN-VALUE", sign);
            request.setEntity(params);
            long startTime = System.currentTimeMillis();
            HttpResponse response = httpClient.execute(request);
            long endTime = System.currentTimeMillis();
            long hanshi = endTime - startTime;
            timeSum += hanshi;
            System.out.println("请求耗费时间为:" + (endTime - startTime));
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
            JSONObject jsonobj = new JSONObject(result);
            int code = jsonobj.getInt("code");
            String message = jsonobj.getString("message");
            if (code == 0 && ("success".equals(message))) {
                isSuccess = true;
            }
            System.out.println("返回标志：" + code);
            System.out.println("返回信息：" + message);
            System.out.println(jsonobj.toString());
            return isSuccess;
        } catch (Exception e) {
            // handle exception here
            e.printStackTrace();
            return isSuccess;
        } finally {
            httpClient.getConnectionManager().shutdown(); //Deprecated
        }
    }

    /**
     * 根据downloadid访问迅雷录入接口
     */
    public static String testDownLoadIdIncomeStatus(String downLoadId) {
        //初始化deviceId
        String devicedId = null;
        System.out.println(downLoadId + "mac地址——————");
        String randomStr = "578f5db6-cac8-4ec6-ae21-b33fffc44e23";
        String ckey = "1924F38DF4982A557D86785B29B0CB11";
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String securityKey = "E9AC45B450630411CE6D8BCE41FF6B33";
        String sign = MD5util.MD5(randomStr + timeStamp + securityKey);
        String requesrParamPrefixStr = "http://60.217.235.176/rope/1.0.0/queryDeviceIdInfo?ct=28&sn=" + downLoadId + "&deviceType=10";
        HttpClient httpClient = HttpClientBuilder.create().build();
        String result = "";
        try {
            HttpGet request = new HttpGet(requesrParamPrefixStr);
            request.addHeader("content-type", "application/json");
            request.addHeader("XL-REQUEST-RANDOM", randomStr);
            request.addHeader("XL-REQUEST-TIMESTAMP", timeStamp);
            request.addHeader("XL-COMPANY-KEY", ckey);
            request.addHeader("XL-SIGN-VALUE", sign);
            long startTime = System.currentTimeMillis();
            HttpResponse httpResponse = httpClient.execute(request);
            long endTime = System.currentTimeMillis();
            long haoshi = endTime - startTime;
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
            //去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
            result.replaceAll("\r", "");
            JSONObject jsonobj = new JSONObject(result);
            JSONObject dataObject = jsonobj.getJSONObject("data");
            String message = jsonobj.getString("message");
            int code = jsonobj.getInt("code");
            if (code == 0 && ("success".equals(message))) {
                devicedId = dataObject.getString("diviceId");
            }
            System.out.println("返回标志：" + code);
            System.out.println("返回信息：" + message);
            System.out.println("返回数据，产品id是：" + devicedId + " 产品类型是：" + dataObject.getString("diviceType"));
        } catch (Exception e) {
            e.printStackTrace();
            return devicedId;
        } finally {
            httpClient.getConnectionManager().shutdown(); //Deprecated
        }
        return devicedId;
    }

    public static void main(String[] args) throws IOException {
        List<String> macStrList = new ArrayList<>();
        macStrList.add("34:BD:F9:C0:C2:F0");
        addDownLoadId(macStrList);
        //testDownLoadIdIncomeStatus();
    }
}
