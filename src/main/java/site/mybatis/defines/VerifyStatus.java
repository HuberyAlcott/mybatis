package site.mybatis.defines;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mybatisflex.annotation.EnumValue;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

/** 审核状态 */
@Getter
@AllArgsConstructor
public enum VerifyStatus {
  invalid(0, "无效的"),
  applied(10, "已申请"),
  waiting(20, "处理中"),
  accepted(40, "已同意"),
  rejected(60, "已拒绝"),
  ;

  @JsonValue @EnumValue private final Integer flag;

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
}
