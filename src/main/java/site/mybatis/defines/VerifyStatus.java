package site.mybatis.defines;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import site.commons.defines.GeneralValuing;

/** 审核状态 */
@Getter
@AllArgsConstructor
public enum VerifyStatus implements GeneralValuing<VerifyStatus> {
  invalid(0, "无效的"),
  applied(10, "已申请"),
  waiting(20, "处理中"),
  accepted(40, "已同意"),
  rejected(60, "已拒绝"),
  ;

  @JsonValue private final Integer flag;

  private final String desc;

  @JsonCreator
  public static VerifyStatus typeof(final Integer type) {
    for (VerifyStatus status : values()) {
      if (Objects.equals(status.getFlag(), type)) {
        return status;
      }
    }
    return invalid;
  }

  @Override
  public VerifyStatus getEnum(Integer flag) {
    return typeof(flag);
  }
}
