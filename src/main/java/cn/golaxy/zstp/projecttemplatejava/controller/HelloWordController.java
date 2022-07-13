package cn.golaxy.zstp.projecttemplatejava.controller;

import cn.golaxy.zstp.projecttemplatejava.domain.ResultObject;
import cn.golaxy.zstp.projecttemplatejava.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zhangshuailing
 * @date ：2022/7/13 15:24
 * @project_name ：project-template-java
 * @description：
 */
@Slf4j
@Api(value = "hello word", description = "hello word", tags = "hello word")
@RestController
@RequestMapping(value = "/hello")
public class HelloWordController {

    @ApiOperation(value = "hello word", notes = "hello word")
    @GetMapping("/")
    public String hello() {
        ResultObject resultObject = new ResultObject(20000, "hello word", null, "");
        return ResultUtil.result(resultObject);
    }
}
