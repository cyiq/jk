package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.ContractProductDao;
import cn.itcast.jk.dao.ExtCproductDao;
import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.ContractProductService;
import cn.itcast.util.UtilFuns;

@Service
public class ContractProductServiceImpl implements ContractProductService{
	@Resource
	ContractProductDao contractProductDao;
	@Resource
	ExtCproductDao extCproductDao;
	
	public List<ContractProduct> findPage(Page page) {
		return contractProductDao.findPage(page);
	}

	public List<ContractProduct> find(Map paraMap) {
		return contractProductDao.find(paraMap);
	}

	public ContractProduct get(Serializable id) {
		return contractProductDao.get(id);
	}

	public void insert(ContractProduct contractProduct) {
		contractProduct.setId(UUID.randomUUID().toString());
		if(UtilFuns.isNotEmpty(contractProduct.getCnumber())&&UtilFuns.isNotEmpty(contractProduct.getPrice()))
		contractProduct.setAmount(contractProduct.getPrice()*contractProduct.getCnumber());
		contractProductDao.insert(contractProduct);
	}

	public void update(ContractProduct contractProduct) {
		contractProductDao.update(contractProduct);
	}

	public void deleteById(Serializable id) {
		Serializable[] ids = {id};
		extCproductDao.deleteByContractProductId(ids);
		contractProductDao.deleteById(id);
	}

	public void delete(Serializable[] ids) {
		extCproductDao.deleteByContractProductId(ids);
		contractProductDao.delete(ids);
	}
	
}