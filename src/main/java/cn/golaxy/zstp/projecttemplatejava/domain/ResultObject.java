package cn.golaxy.zstp.projecttemplatejava.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：yaojianlin(yaojianlin@golaxy.cn)
 * @date ：Created in 17:51 2021/7/19
 * @description：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("返回结果")
public class ResultObject {
    @ApiModelProperty("请求状态消息")
    private int code;
    @ApiModelProperty("具体返回数据")
    private Object data;
    @ApiModelProperty("分页信息")
    private Pager pager;
    @ApiModelProperty("说明")
    private String message;
}
