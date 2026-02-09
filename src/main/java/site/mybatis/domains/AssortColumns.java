package site.mybatis.domains;

import com.mybatisflex.annotation.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Comparator;
import java.util.Objects;

/**
 * 排序字段类
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class AssortColumns extends CommonColumns
    implements Comparable<Integer>, Comparator<Integer> {

  /**
   * 排序编号
   */
  @Column(value = "sorted_number")
  private Integer sortedNumber;

  /**
   * 排序比较
   */
  @Override
  public int compare(Integer o1, Integer o2) {
    if (o1 == null && o2 == null) return 0;
    if (o1 == null) return -1;
    if (o2 == null) return 1;
    return o1 - o2;
  }

  /**
   * 比较对象
   */
  @Override
  public int compareTo(Integer o) {
    return compare(this.getSortedNumber(), o);
  }

  @Override
  public void beforeCreate() {
    super.beforeCreate();
    if (Objects.isNull(this.getSortedNumber())) {
      this.setSortedNumber(0);
    }
  }

  @Override
  public void beforeUpdate() {
    super.beforeUpdate();
    if (Objects.isNull(this.getSortedNumber())) {
      this.setSortedNumber(0);
    }
  }
}
