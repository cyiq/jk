package cn.itcast.jk.dao.impl;

import java.io.Serializable;
import java.util.Map;	

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.ExportProductDao;
import cn.itcast.jk.domain.ExportProduct;
@Repository
public class ExportProductDaoImpl extends BaseDaoImpl<ExportProduct> implements ExportProductDao {
	public ExportProductDaoImpl() {
		super.setNs("cn.itcast.jk.mapper.ExportProductMapper");
	}


}
