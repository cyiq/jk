package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.FactoryDao;
import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.FactoryService;

@Service
public class FactoryServiceImpl implements FactoryService {
	@Resource
	FactoryDao factoryDao;
	public List<Factory> findPage(Page page) {
		return null;
	}

	public List<Factory> find(Map paraMap) {
		return factoryDao.find(paraMap);
	}

	public Factory get(Serializable id) {
		return factoryDao.get(id);
	}

	public void insert(Factory factory) {
		factory.setId(UUID.randomUUID().toString());
		factory.setState("1");			//默认启用状态
		factoryDao.insert(factory);
	}

	public void update(Factory factory) {
		factoryDao.update(factory);
	}

	public void deleteById(Serializable id) {
		factoryDao.deleteById(id);
	}

	public void delete(Serializable[] ids) {
		factoryDao.delete(ids);
	}

	public void start(Serializable[] ids) {
		Map map = new HashMap();
		map.put("state", "1");
		map.put("ids", ids);
		factoryDao.updateState(map);
	}

	public void stop(Serializable[] ids) {
		Map map = new HashMap();
		map.put("state", "0");
		map.put("ids", ids);
		factoryDao.updateState(map);
	}

	public List<Factory> getFactoryList() {
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("state", "1");
		return factoryDao.find(paraMap);
	}

}
