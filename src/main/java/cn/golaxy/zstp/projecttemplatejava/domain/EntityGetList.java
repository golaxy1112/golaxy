package cn.golaxy.zstp.projecttemplatejava.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ：yaojianlin(yaojianlin@golaxy.cn)
 * @date ：Created in 2022/4/6 11:22
 * @description：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "列表查询条件", description = "列表查询条件")
public class EntityGetList {

    @ApiModelProperty(value = "id列表", required = true)
    public List<String> ids;
    @ApiModelProperty(value = "name", required = true)
    public String name;

}
