package team.keephealth.xyj.modules.keephealth.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PasswordDto {
    @ApiModelProperty(value = "修改的新密码")
    private String password;
}
