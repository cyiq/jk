package cn.itcast.jk.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.FactoryDao;
import cn.itcast.jk.domain.Factory;
@Repository
public class FactoryDaoImpl extends BaseDaoImpl<Factory> implements FactoryDao {
	public FactoryDaoImpl() {
		super.setNs("cn.itcast.jk.mapper.FactoryMapper");
	}

	public void updateState(Map map) {
		this.getSqlSession().update(super.getNs()+".updateState",map);
	}
}
