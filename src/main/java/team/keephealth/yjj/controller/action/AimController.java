package team.keephealth.yjj.controller.action;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team.keephealth.common.annotation.SystemLog;
import team.keephealth.xyj.modules.keephealth.domain.ResponseResult;
import team.keephealth.xyj.modules.keephealth.domain.dto.TarProfileDto;
import team.keephealth.xyj.modules.keephealth.service.UserProfileService;
import team.keephealth.yjj.domain.dto.action.AimInfoDto;
import team.keephealth.yjj.domain.vo.ResultVo;
import team.keephealth.yjj.service.action.AimService;

import javax.annotation.Resource;

@Api(tags = {"我的每日目标接口"})
@RestController
@RequestMapping("/aim")
public class AimController {

    @Resource
    private AimService aimService;
    @Resource
    private UserProfileService userProfileService;

    @ApiOperation(value = "获取今日卡路里记录")
    @SystemLog(businessName = "获取今日卡路里记录")
    @GetMapping("/today")
    public ResultVo<T> queryToday(){
        return aimService.kcalMessage();
    }

    @ApiOperation(value = "获取目标列表")
    @SystemLog(businessName = "获取目标列表")
    @GetMapping("/aims")
    public ResultVo<T> queryAims(){
        return aimService.aims();
    }

    @ApiOperation(value = "更改我的目标")
    @SystemLog(businessName = "更改我的目标")
    @PutMapping("/update")
    public ResultVo<T> updateAims(@RequestBody AimInfoDto dto){
        return aimService.updateAim(dto);
    }

    @ApiOperation(value = "添加/编辑用户目标体重档案")
    @SystemLog(businessName = "添加/编辑用户目标体重档案")
    @PostMapping("/tar")
    @PreAuthorize("hasAuthority('user:profile:add')")
    public ResponseResult addUserTarProfile(@RequestBody TarProfileDto profileDto) {
        return userProfileService.addUserTarProfile(profileDto);
    }
}
