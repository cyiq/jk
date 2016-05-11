package cn.itcast.jk.service;

import java.util.List;
import java.util.Map;

import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.vo.ContractVO;


public interface ContractHisService {
	public void pigeinhole(String[] contractIds);		//归档
	public void pigeouthole(String[] contractIds);		//取消归档
	
	public List<Contract> findPage(Page page);		//分页查询
	public List<Contract> find(Map paraMap);		//带条件查询，条件可以为null，既没有条件；返回list对象集合
	public ContractVO view(String contractId);		//关联对象的查询一个
}
