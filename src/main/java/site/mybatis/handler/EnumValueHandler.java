package site.mybatis.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import site.commons.defines.GeneralValuing;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public abstract class EnumValueHandler<E extends GeneralValuing<E>> extends BaseTypeHandler<E> {

  private E e;

  void init() {
    try {
      final Type type = super.getRawType();
      if (type instanceof ParameterizedType) {
        final ParameterizedType paramType = (ParameterizedType) type;
        this.e = (E) paramType.getActualTypeArguments()[0];
      }
    } catch (Throwable _ex) {

    }
  }

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
    ps.setInt(i, parameter.getFlag());
  }

  @Override
  public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return e.getEnum(rs.getInt(columnName));
  }

  @Override
  public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return e.getEnum(rs.getInt(columnIndex));
  }

  @Override
  public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return e.getEnum(cs.getInt(columnIndex));
  }
}
