package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.Export;
import cn.itcast.jk.pagination.Page;

public interface ExportService {
	public List<Export> findPage(Page page);		//分页查询
	public List<Export> find(Map paraMap);			//带条件查询，条件可以为null，既没有条件；返回list对象集合
	public Export get(String id);				//只查询一个，常用于修改
	public void insert(String[] contractIds);				//插入，用实体作为参数
	public void update(Export export);				//修改，用实体作为参数
	public void deleteById(Serializable id);		//按id删除，删除一条；支持整数型和字符串类型ID
	public void delete(Serializable[] ids);			//批量删除；支持整数型和字符串类型ID
	
	public void submit(Serializable[] ids);			//	取消
	public void cancle(Serializable[] ids);			//	上报
	
	public List<Contract> getContractList();
	public String getMrecordData(String exportId);
	public void update(
			Export export, 
			String[] mr_id, 
			Integer[] mr_orderNo,
			Integer[] mr_cnumber, 
			Double[] mr_grossWeight,
			Double[] mr_netWeight, 
			Double[] mr_sizeLength,
			Double[] mr_sizeWidth, 
			Double[] mr_sizeHeight, 
			Double[] mr_exPrice,
			Double[] mr_tax, 
			Integer[] mr_changed
		);
}
