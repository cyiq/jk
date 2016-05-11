package cn.itcast.jk.dao;

import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.vo.ContractVO;

public interface ContractHisDao extends BaseDao<Contract> {
	public ContractVO view(String contractId);		//查询某个合同
}
