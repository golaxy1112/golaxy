package cn.golaxy.zstp.projecttemplatejava.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：yaojianlin(yaojianlin@golaxy.cn)
 * @date ：Created in 17:45 2021/7/19
 * @description：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分页信息")
public class Pager {
    @ApiModelProperty("从第多少个数据开始")
    private int start;
    @ApiModelProperty("单页返回数量限制")
    private int limit;
    @ApiModelProperty("总数")
    private int total;
}
