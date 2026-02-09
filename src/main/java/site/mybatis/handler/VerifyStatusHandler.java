package site.mybatis.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import site.commons.defines.status.VerifyStatus;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class VerifyStatusHandler extends BaseTypeHandler<VerifyStatus> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, VerifyStatus ev, JdbcType jt) throws SQLException {
    ps.setInt(i, ev.getFlag());
  }

  @Override
  public VerifyStatus getNullableResult(ResultSet rs, String cn) throws SQLException {
    return VerifyStatus.typeof(rs.getInt(cn));
  }

  @Override
  public VerifyStatus getNullableResult(ResultSet rs, int ci) throws SQLException {
    return VerifyStatus.typeof(rs.getInt(ci));
  }

  @Override
  public VerifyStatus getNullableResult(CallableStatement cs, int ci) throws SQLException {
    return VerifyStatus.typeof(cs.getInt(ci));
  }
}
