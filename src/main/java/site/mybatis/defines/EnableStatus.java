package site.mybatis.defines;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mybatisflex.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 可用状态
 *
 * @author Marcus
 */
@Getter
@AllArgsConstructor
public enum EnableStatus {

  /* 未知状态 */
  UNKNOWN(0, "status.domains.enable.unknown"),
  /* 当前可用 */
  ENABLED(1, "status.domains.enable.enabled"),
  /* 当前不可用 */
  DISABLE(2, "status.domains.enable.disable"),
  ;

  @JsonValue @EnumValue private final Integer flag;
  private final String desc;

  @JsonCreator
  public static EnableStatus typeof(Integer code) {
    for (EnableStatus value : EnableStatus.values()) {
      if (value.getFlag().equals(code)) {
        return value;
      }
    }
    return UNKNOWN;
  }
}
