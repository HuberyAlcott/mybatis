package site.mybatis.domains;

import com.mybatisflex.annotation.Column;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import site.commons.defines.status.DeleteStatus;
import site.commons.defines.status.EnableStatus;
import site.mybatis.handler.DeleteStatusHandler;
import site.mybatis.handler.EnableStatusHandler;

/** 默认字段类 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode
public abstract class CommonColumns implements Serializable {

  /** 数据标识 */
  @Column(value = "unique_serial")
  private String uniqueSerial;

  /** 上级编号 */
  @Column(value = "extend_serial")
  private Long extendSerial;

  /** 继承路径 */
  @Column(value = "extend_tracks")
  private String extendTracks;

  /** 租户编号 */
  @Column(value = "tenant_serial")
  private Long tenantSerial;

  /** 可用状态 */
  @Column(value = "enable_status", typeHandler = EnableStatusHandler.class)
  private EnableStatus enableStatus;

  /** 删除状态 */
  @Column(value = "delete_status", typeHandler = DeleteStatusHandler.class)
  private DeleteStatus deleteStatus;

  /** 创建人名 */
  @Column(value = "create_naming")
  private String createNaming;

  /** 创建时间 */
  @Column(value = "create_timing")
  private Date createTiming;

  /** 更新人名 */
  @Column(value = "update_naming")
  private String updateNaming;

  /** 更新时间 */
  @Column(value = "update_timing")
  private Date updateTiming;

  /** 更新次数 */
  @Column(value = "locked_counts")
  private Integer lockedCounts;

  /** 数据版本 */
  @Column(value = "edited_serial")
  private String editedSerial;

  public void resetStringSerial() {
    this.setUniqueSerial(UUID.randomUUID().toString().substring(0, 8));
  }

  public void resetStringSerial(String serial) {
    this.setUniqueSerial(serial);
  }

  /** 创建之前 */
  public void beforeCreate() {
    final Timestamp time = Timestamp.valueOf(LocalDateTime.now());
    if (Objects.isNull(this.getUniqueSerial())) {
      this.setUniqueSerial(UUID.randomUUID().toString().substring(0, 8));
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
      this.setEnableStatus(EnableStatus.ENABLED);
    }
    if (Objects.isNull(this.getDeleteStatus())) {
      this.setDeleteStatus(DeleteStatus.ENABLED);
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

  /** 更新之前 */
  public void beforeUpdate() {
    if (Objects.isNull(this.getUpdateTiming())) {
      this.setUpdateTiming(Timestamp.valueOf(LocalDateTime.now()));
    }
    final Integer counts = this.getLockedCounts();
    this.setLockedCounts(Objects.isNull(counts) ? 1 : counts + 1);
  }
}
