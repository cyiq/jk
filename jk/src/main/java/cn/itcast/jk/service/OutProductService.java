package cn.itcast.jk.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.itcast.jk.dao.OutProductDao;
import cn.itcast.jk.vo.OutProductVO;

public interface OutProductService {
	public List<OutProductVO> find(String inputDate);		//带条件查询，条件可以为null，既没有条件；返回list对象集合
}
