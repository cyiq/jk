package cn.itcast.jk.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.ContractHisDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.vo.ContractVO;

@Repository
public class ContractHisDaoImpl extends BaseDaoImpl<Contract> implements ContractHisDao{
	public ContractHisDaoImpl() {
		//设置命名空间
		super.setNs("cn.itcast.jk.mapper.ContractHisMapper");
	}

	public ContractVO view(String contractId) {
		return super.getSqlSession().selectOne(super.getNs()+".view", contractId);
	}
}
