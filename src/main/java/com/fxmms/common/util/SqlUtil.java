package com.fxmms.common.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SqlUtil {
	protected static final Log logger = LogFactory.getLog(SqlUtil.class);

	private SqlUtil() {
	}

	public static Character rsGetChar(ResultSet rs, String columnLabel) {
		if (rs == null || columnLabel == null) {
			return null;
		}
		String str = null;
		try {
			str = rs.getString(columnLabel);
		} catch (SQLException e) {
			logger.warn(e, e);
			return null;
		}
		if (str == null || str.isEmpty()) {
			return null;
		}
		return str.charAt(0);
	}

	public static Long rsGetLong(ResultSet rs, String columnLabel) {
		if (rs == null || columnLabel == null) {
			return null;
		}
		Long val = null;
		try {
			val = rs.getLong(columnLabel);
			if (rs.wasNull()) {
				val = null;
			}
		} catch (SQLException e) {
			logger.warn(e, e);
			return null;
		}
		return val;
	}
}
