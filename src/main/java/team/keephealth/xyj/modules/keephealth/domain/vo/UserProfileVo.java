package team.keephealth.xyj.modules.keephealth.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class UserProfileVo {
    //用户出生日期
    private Date userBirthday;
    //体重（kg/公斤）
    private Double userWeight;
    //用户身高（cm）
    private Double userHeight;
    //目标体重（kg/公斤）
    private Double userTargetWeight;
    //目标达成速度
    private Date targetFinishTime;
}
