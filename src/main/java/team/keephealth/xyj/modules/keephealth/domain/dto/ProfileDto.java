package team.keephealth.xyj.modules.keephealth.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ProfileDto {
    //用户出生日期
    @ApiModelProperty(value = "用户出生日期 格式（yyyy-MM-dd）")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date userBirthday;
    //体重（kg/公斤）
    @ApiModelProperty(value = "体重（kg/公斤）（1位小数）")
    private Double userWeight;
    //用户身高（cm）
    @ApiModelProperty(value = "用户身高（cm）（1位小数）")
    private Double userHeight;
    //目标体重（kg/公斤）
    @ApiModelProperty(value = "目标体重（kg/公斤）（1位小数）")
    private Double userTargetWeight;
}
