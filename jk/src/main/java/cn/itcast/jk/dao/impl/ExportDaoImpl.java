package cn.itcast.jk.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.ExportDao;
import cn.itcast.jk.domain.Export;


@Repository
public class ExportDaoImpl extends BaseDaoImpl<Export> implements ExportDao {
	public ExportDaoImpl() {
		super.setNs("cn.itcast.jk.mapper.ExportMapper");
	}

	public void updateState(Map map) {
		this.getSqlSession().update(super.getNs()+".updateState",map);
	}

}
