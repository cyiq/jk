package cn.itcast.jk.dao.impl;

import org.springframework.stereotype.Repository;	

import cn.itcast.jk.dao.ExtEproductDao;
import cn.itcast.jk.domain.ExtEproduct;

@Repository
public class ExtEproductDaoImpl extends BaseDaoImpl<ExtEproduct> implements ExtEproductDao {
	public ExtEproductDaoImpl() {
		super.setNs("cn.itcast.jk.mapper.ExtEproductMapper");
	}
}
