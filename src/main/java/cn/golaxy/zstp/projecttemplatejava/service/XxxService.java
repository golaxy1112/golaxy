package cn.golaxy.zstp.projecttemplatejava.service;

import cn.golaxy.zstp.projecttemplatejava.domain.EntityGetList;
import cn.golaxy.zstp.projecttemplatejava.domain.ResultObject;
import org.springframework.stereotype.Service;

/**
 * @author ：yaojianlin(yaojianlin@golaxy.cn)
 * @date ：Created in 17:57 2021/7/19
 * @description：
 */
public interface XxxService {
    /**
     *
     * @param start 开始下标
     * @param limit 每页条数
     * @return 返回结果
     */
    ResultObject getList(int start, int limit, EntityGetList mapBody);
}
