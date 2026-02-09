package site.mybatis.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import site.commons.defines.status.DeleteStatus;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class DeleteStatusHandler extends BaseTypeHandler<DeleteStatus> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, DeleteStatus ev, JdbcType jt) throws SQLException {
    ps.setInt(i, ev.getFlag());
  }

  @Override
  public DeleteStatus getNullableResult(ResultSet rs, String cn) throws SQLException {
    return DeleteStatus.typeof(rs.getInt(cn));
  }

  @Override
  public DeleteStatus getNullableResult(ResultSet rs, int ci) throws SQLException {
    return DeleteStatus.typeof(rs.getInt(ci));
  }

  @Override
  public DeleteStatus getNullableResult(CallableStatement cs, int ci) throws SQLException {
    return DeleteStatus.typeof(cs.getInt(ci));
  }
}
