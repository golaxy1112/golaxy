package cn.golaxy.zstp.projecttemplatejava.service.impl;

import cn.golaxy.zstp.projecttemplatejava.dao.XxxDao;
import cn.golaxy.zstp.projecttemplatejava.domain.EntityGetList;
import cn.golaxy.zstp.projecttemplatejava.domain.ResultObject;
import cn.golaxy.zstp.projecttemplatejava.service.XxxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：yaojianlin(yaojianlin@golaxy.cn)
 * @date ：Created in 18:02 2021/7/19
 * @description：
 */
@Slf4j
@Service
public class XxxServiceImpl implements XxxService {
    private final XxxDao xxxDao;

    @Autowired
    public XxxServiceImpl(XxxDao xxxDao) {
        this.xxxDao = xxxDao;
    }

    @Override
    public ResultObject getList(int start, int limit, EntityGetList mapBody) {
        log.info("==========列表查询==========");
        List<String> resultList = xxxDao.getList(start, limit);
        return new ResultObject(20000, resultList, null, "");
    }
}
