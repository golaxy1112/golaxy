package cn.golaxy.zstp.projecttemplatejava.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author ：yaojianlin(yaojianlin@golaxy.cn)
 * @date ：Created in 19:41 2021/7/19
 * @description： 通用工具类
 */
public class Utils {

    /**
     * 生成唯一字符串
     * @return 结果
     */
    public static String getUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * 对象转JSON
     * @param obj 对象
     * @return 结果
     */
    public static String objectToJson(Object obj){
        return JSONObject.toJSONString(obj);
    }

    /**
     * json字符串转map
     * @param jsonStr JSON字符串
     * @return 结果
     */
    public static Map<String, Object> jsonToMap(String jsonStr){
        Map<String, Object> map = new HashMap<>();
        try {
            map = (Map<String, Object>) JSON.parse(jsonStr);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 关闭流
     * @param is 输入流
     * @param os 输出流
     */
    public static void closeStream(InputStream is, OutputStream os){
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据路径删除文件
     * @param path 路径
     */
    public static void deleteFile(String path){
        File file = new File(path);
        if(file.exists()){
            file.delete();
        }
    }

    /**
     * 复制map
     * @param map 参数
     * @return 返回map
     */
    public static Map<String,Object> copyMap(Map<String,Object> map){
        Map<String,Object> return_map = new HashMap<>();
        if(map!=null){
            for (Map.Entry<String,Object> k: map.entrySet()) {
                return_map.put(k.getKey(),k.getValue());
            }
        }
        return return_map;
    }
}
