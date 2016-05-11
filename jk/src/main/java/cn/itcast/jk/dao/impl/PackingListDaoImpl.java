package cn.itcast.jk.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.PackingListDao;
import cn.itcast.jk.domain.PackingList;


@Repository
public class PackingListDaoImpl extends BaseDaoImpl<PackingList> implements PackingListDao {
	public PackingListDaoImpl() {
		super.setNs("cn.itcast.jk.mapper.PackingListMapper");
	}

	public void updateState(Map map) {
		this.getSqlSession().update(super.getNs()+".updateState",map);
	}

}
