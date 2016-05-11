package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.ContractDao;
import cn.itcast.jk.dao.ContractProductDao;
import cn.itcast.jk.dao.ExtCproductDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.ContractService;
import cn.itcast.jk.vo.ContractVO;

@Service
public class ContractServiceImpl implements ContractService{
	@Resource
	ContractDao contractDao;
	@Resource
	ContractProductDao contractProductDao;
	@Resource
	ExtCproductDao extCproductDao;
	public List<Contract> findPage(Page page) {
		return contractDao.findPage(page);
	}

	public List<Contract> find(Map paraMap) {
		return contractDao.find(paraMap);
	}

	public Contract get(Serializable id) {
		return contractDao.get(id);
	}

	public void insert(Contract contract) {
		contract.setId(UUID.randomUUID().toString());
		contract.setState(0);		//	初次增加时设为草稿状态
		contractDao.insert(contract);
	}

	public void update(Contract contract) {
		contractDao.update(contract);
	}

	public void deleteById(Serializable id) {
		Serializable [] ids = {id};
		extCproductDao.deleteByContractId(ids);			//删除合同下的附件信息
		contractProductDao.deleteByContractId(ids);		//删除合同下的货物信息
		contractDao.deleteById(id);
	}
	
	public void delete(Serializable[] ids) {
		extCproductDao.deleteByContractId(ids);			//删除合同下的附件信息
		contractProductDao.deleteByContractId(ids);
		contractDao.delete(ids);
	}

	public void submit(Serializable[] ids) {			//已上报
		Map map = new HashMap();
		map.put("state", 1);
		map.put("ids", ids);
		
		contractDao.updateState(map);
	}

	public void cancle(Serializable[] ids) {	//	草稿状态
		Map map = new HashMap();
		map.put("state", 0);
		map.put("ids", ids);
		
		contractDao.updateState(map);
	}

	@Override
	public ContractVO view(String contractId) {
		return contractDao.view(contractId);
	}
	
	
}