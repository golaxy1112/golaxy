package cn.golaxy.zstp.projecttemplatejava.util;

import cn.golaxy.zstp.projecttemplatejava.domain.ResultObject;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author ：yaojianlin(yaojianlin@golaxy.cn)
 * @date ：Created in 17:55 2021/7/19
 * @description：
 */
public class ResultUtil {
    public static String result(ResultObject resultObject){
        return JSONObject.toJSONString(resultObject, SerializerFeature.DisableCircularReferenceDetect);
    }
}
