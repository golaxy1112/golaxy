package cn.golaxy.zstp.projecttemplatejava.controller;

import cn.golaxy.zstp.projecttemplatejava.domain.EntityGetList;
import cn.golaxy.zstp.projecttemplatejava.domain.ResultObject;
import cn.golaxy.zstp.projecttemplatejava.service.XxxService;
import cn.golaxy.zstp.projecttemplatejava.util.ResultUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：yaojianlin(yaojianlin@golaxy.cn)
 * @date ：Created in 17:18 2021/7/19
 * @description：
 */
@Slf4j
@Api(value = "desc of class", description = "描述信息", tags = "测试类接口API")
@RestController
@RequestMapping(value = "/xxx")
public class XxxController {

    private final XxxService xxxService;

    @Autowired
    public XxxController(XxxService xxxService) {
        this.xxxService = xxxService;
    }

    @ApiOperation(value = "测试连接", notes = "测试接口")
    @GetMapping("/index")
    public String index() {
        ResultObject resultObject = new ResultObject(20000, System.currentTimeMillis(), null, "");
        return ResultUtil.result(resultObject);
    }

    @ApiOperation(value = "获取列表", notes = "获取列表")
    @ApiResponses({
            @ApiResponse(code = 20000, message = "正确返回状态码", response = ResultObject.class),
            @ApiResponse(code = 20005, message = "错误返回状态码", response = ResultObject.class),
    })
    @PostMapping("/getList")
    public ResultObject getList(
            @ApiParam(value = "起始下标") @RequestParam(required = false, defaultValue = "0") int start,
            @ApiParam(value = "限制条数") @RequestParam(required = false, defaultValue = "10") int limit,
            @RequestBody EntityGetList mapBody) {
        return xxxService.getList(start, limit, mapBody);
    }
}
