package team.keephealth.xyj.modules.keephealth.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 通过id获取用户关注列表
 */
@Data
public class SelectOtherWatchPageDto {
    //页码
    @ApiModelProperty(value = "页码")
    private Integer pageNum;
    //每页数据条数
    @ApiModelProperty(value = "每页数据条数")
    private Integer pageSize;
    //用户id
    @ApiModelProperty(value = "用户id")
    private Long userId;

    //用户状态（0正常 1停用）
    @ApiModelProperty(value = "用户状态（0正常 1停用）")
    private String state;
    //用户类型：0代表普通用户 1代表第三方工作人员，2代表管理员，
    @ApiModelProperty(value = "用户类型：0代表普通用户 1代表第三方工作人员，2代表管理员")
    private String type;
    //搜索用户名的关键词
    @ApiModelProperty(value = "搜索用户名的关键词")
    private String keyword;
}
