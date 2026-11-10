package site.mybatis.defines;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import site.commons.defines.GeneralValuing;

/**
 * 删除状态
 *
 * @author Marcus
 */
@Getter
@AllArgsConstructor
public enum DeleteStatus implements GeneralValuing<DeleteStatus> {

  /* 未知状态 */
  UNKNOWN(0, "status.domains.delete.unknown"),
  /* 当前未删除 */
  ENABLED(1, "status.domains.delete.enabled"),
  /* 当前已删除 */
  DELETED(2, "status.domains.delete.deleted"),
  ;
  @JsonValue private final Integer flag;
  private final String desc;

  @JsonCreator
  public static DeleteStatus typeof(Integer state) {
    for (DeleteStatus value : DeleteStatus.values()) {
      if (Objects.equals(value.getFlag(), state)) {
        return value;
      }
    }
    return UNKNOWN;
  }

  @Override
  public DeleteStatus getEnum(Integer flag) {
    return typeof(flag);
  }
}
