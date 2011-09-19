/**
 * 
 */
package com.jchatting;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.jchatting.db.bean.Friend;
import com.jchatting.db.persistent.DbPoolUtil;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-17 下午4:00:15
 * 
 */
public class Test {

	/**
	 * 
	 */
	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = DbPoolUtil.getInstance().getConnection();
		try {
			QueryRunner qr = new QueryRunner();
			List<?> results = qr.query(conn,
					"select USER_ID, FRIEND_ID from FRIEND;",
					new MapListHandler());
			for (int i = 0; i < results.size(); i++) {
				Map<?, ?> map = (Map<?, ?>)results.get(i);
				System.out.println(" userid: " + map.get(Friend.USER_ID)
						+ " ,frieng_id: " + map.get(Friend.FRIEND_ID));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}
}
