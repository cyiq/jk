package cn.itcast.jk.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;	

import cn.itcast.jk.dao.ExtCproductDao;
import cn.itcast.jk.domain.ExtCproduct;

@Repository
public class ExtCproductDaoImpl extends BaseDaoImpl<ExtCproduct> implements ExtCproductDao {
	public ExtCproductDaoImpl() {
		super.setNs("cn.itcast.jk.mapper.ExtCproductMapper");
	}

	@Override
	public void deleteByContractProductId(Serializable[] ids) {
		super.getSqlSession().delete(super.getNs()+".deleteByContractProductId", ids);
	}
	
	public void deleteByContractId(Serializable[] ids){
		super.getSqlSession().delete(super.getNs()+".deleteByContractId", ids);
	}
	
}
