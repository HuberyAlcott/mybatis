package site.mybatis.domains;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import site.commons.defines.status.DeleteStatus;
import site.commons.defines.status.EnableStatus;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * 默认字段类
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode
public abstract class CommonColumns implements Serializable {

  /**
   * 数据标识
   */
  private String stringSerial;

  /**
   * 上级编号
   */
  private Long extendSerial;


  /**
   * 继承路径
   */
  private String extendTracks;
  /**
   * 租户编号
   */
  private Long tenantSerial;

  /**
   * 可用状态
   */
  private Integer enableStatus;

  /**
   * 删除状态
   */
  private Integer deleteStatus;

  /**
   * 创建人名
   */
  private String createNaming;

  /**
   * 创建时间
   */
  private Date createTiming;

  /**
   * 更新人名
   */
  private String updateNaming;

  /**
   * 更新时间
   */
  private Date updateTiming;

  /**
   * 更新次数
   */
  private Integer lockedCounts;

  /**
   * 数据版本
   */
  private String editedSerial;

  public void resetStringSerial() {
    this.setStringSerial(UUID.randomUUID().toString().substring(0, 8));
  }

  public void resetStringSerial(String serial) {
    this.setStringSerial(serial);
  }

  /**
   * 创建之前
   */
  public void beforeCreate() {
    final Timestamp time = Timestamp.valueOf(LocalDateTime.now());
    if (Objects.isNull(this.getStringSerial())) {
      this.setStringSerial(UUID.randomUUID().toString().substring(0, 8));
    }
    if (Objects.isNull(this.getExtendSerial())) {
      this.setExtendSerial(0L);
    }
    if (Objects.isNull(this.getExtendTracks())) {
      this.setExtendTracks("0");
    }
    if (Objects.isNull(this.getTenantSerial())) {
      this.setTenantSerial(0L);
    }
    if (Objects.isNull(this.getEnableStatus())) {
      this.setEnableStatus(EnableStatus.ENABLED.getFlag());
    }
    if (Objects.isNull(this.getDeleteStatus())) {
      this.setDeleteStatus(DeleteStatus.ENABLED.getFlag());
    }
    if (Objects.isNull(this.getCreateNaming())) {
      this.setCreateNaming("sys_auto");
    }
    if (Objects.isNull(this.getUpdateNaming())) {
      this.setUpdateNaming("sys_auto");
    }
    if (Objects.isNull(this.getCreateTiming())) {
      this.setCreateTiming(time);
    }
    if (Objects.isNull(this.getUpdateTiming())) {
      this.setUpdateTiming(time);
    }
    if (Objects.isNull(this.getLockedCounts())) {
      this.setLockedCounts(0);
    }
    if (Objects.isNull(this.getEditedSerial())) {
      this.setEditedSerial(time.getTime() + "");
    }
  }

  /**
   * 更新之前
   */
  public void beforeUpdate() {
    if (Objects.isNull(this.getUpdateTiming())) {
      this.setUpdateTiming(Timestamp.valueOf(LocalDateTime.now()));
    }
    final Integer counts = this.getLockedCounts();
    this.setLockedCounts(Objects.isNull(counts) ? 1 : counts + 1);
  }
}
