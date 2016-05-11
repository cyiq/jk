package cn.itcast.jk.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.ContractDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.vo.ContractVO;


@Repository
public class ContractDaoImpl extends BaseDaoImpl<Contract> implements ContractDao {
	public ContractDaoImpl() {
		super.setNs("cn.itcast.jk.mapper.ContractMapper");
	}

	public void updateState(Map map) {
		this.getSqlSession().update(super.getNs()+".updateState",map);
	}

	@Override
	public ContractVO view(String contractId) {
		return this.getSqlSession().selectOne(super.getNs()+".view",contractId);
	}
}
