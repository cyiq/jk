package cn.itcast.jk.dao.impl;

import java.io.Serializable;
import java.util.Map;	

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.ContractProductDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.ContractProduct;
@Repository
public class ContractProductDaoImpl extends BaseDaoImpl<ContractProduct> implements ContractProductDao {
	public ContractProductDaoImpl() {
		super.setNs("cn.itcast.jk.mapper.ContractProductMapper");
	}

	@Override
	public void deleteByContractId(Serializable[] ids) {
		super.getSqlSession().delete(super.getNs()+".deleteByContractId", ids);
	}

}
