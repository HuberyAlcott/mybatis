package site.mybatis.enhance;

import static site.commons.defines.CommonsMessage.process_succeed;

import com.mybatisflex.core.paginate.Page;
import java.util.Collection;
import site.commons.defines.GeneralExecute;
import site.commons.defines.GeneralService;
import site.commons.domains.Paged;
import site.commons.results.MRP;

public interface CommonsService extends GeneralService {

  /**
   * 封装分页信息
   *
   * @param page 分页信息
   * @return 分页数据
   * @param <A> 实体类型
   */
  default <A> MRP<A> paging(final Page<A> page) {
    final Paged<A> paged = convert(page, page.getRecords());
    return paging(process_succeed, paged);
  }

  /**
   * 封装分页信息
   *
   * @param info 返回状态
   * @param page 分页信息
   * @return 分页数据
   * @param <A> 实体类型
   */
  default <A> MRP<A> paging(final GeneralExecute info, final Page<A> page) {
    final Paged<A> paged = convert(page, page.getRecords());
    return paging(info, paged);
  }

  /**
   * 封装分页信息
   *
   * @param page 分页信息
   * @param list 实际数据
   * @return 分页数据
   * @param <A> 实体类型
   * @param <F> 原始类型
   */
  default <A, F> MRP<A> paging(final Page<F> page, final Collection<A> list) {
    final Paged<A> paged = convert(page, list);
    return paging(process_succeed, paged);
  }

  /**
   * 转换分页数据
   *
   * @param page 分页信息
   * @return 分页数据
   * @param <A> 实体类型
   * @param <F> 原始类型
   */
  default <A, F> Paged<A> convert(final Page<F> source, final Collection<A> attach) {
    final long page = source.getPageNumber();
    final long size = source.getPageSize();
    final long skip = calSkip(page, size);
    final long sums = source.getTotalRow();
    final Paged<A> target = new Paged<>();
    target.setPage(page < 0 ? 0 : page);
    target.setSize(size < 0 ? 20 : size);
    target.setSkip(skip);
    target.setSums(sums);
    target.setList(attach);
    return target;
  }
}
