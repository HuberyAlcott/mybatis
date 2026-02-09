package site.mybatis.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import site.commons.defines.status.EnableStatus;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class EnableStatusHandler extends BaseTypeHandler<EnableStatus> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, EnableStatus ev, JdbcType jt) throws SQLException {
    ps.setInt(i, ev.getFlag());
  }

  @Override
  public EnableStatus getNullableResult(ResultSet rs, String cn) throws SQLException {
    return EnableStatus.typeof(rs.getInt(cn));
  }

  @Override
  public EnableStatus getNullableResult(ResultSet rs, int ci) throws SQLException {
    return EnableStatus.typeof(rs.getInt(ci));
  }

  @Override
  public EnableStatus getNullableResult(CallableStatement cs, int ci) throws SQLException {
    return EnableStatus.typeof(cs.getInt(ci));
  }
}
