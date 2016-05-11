package cn.itcast.jk.controller.cargo.export;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.Export;
import cn.itcast.jk.service.ExportService;

@Controller
public class ExportController extends BaseController{
	@Resource
	ExportService exportService;
	
	@RequestMapping("/cargo/export/list.action")
	public String list(Model model){
		List<Export> dataList = exportService.find(null);
		model.addAttribute("dataList", dataList);
		return "/cargo/export/jExportList.jsp";
	}
	@RequestMapping("/cargo/export/contractList.action")
	public String contractList(Model model){
		List<Contract> dataList = exportService.getContractList();
		model.addAttribute("dataList", dataList);
		return "/cargo/export/jContractList.jsp";
	}
	
	//报运新增
	@RequestMapping("/cargo/export/insert.action")
	public String insert(@RequestParam("id")String[] contractIds){
		exportService.insert(contractIds);
		return "redirect:/cargo/export/list.action";
	}
	@RequestMapping("/cargo/export/toupdate.action")
	public String toupdate(String id,Model model){
		Export obj = exportService.get(id);
		model.addAttribute("obj", obj);
		//批量修改控件的数据
		model.addAttribute("mRecordData",exportService.getMrecordData(id));
		
		return "/cargo/export/jExportUpdate.jsp";
	}
	@RequestMapping("/cargo/export/update.action")
	public String update(Export export,
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
		){
	exportService.update(export,
			mr_id,
			mr_orderNo,
			mr_cnumber,
			mr_grossWeight,
			mr_netWeight,
			mr_sizeLength,
			mr_sizeWidth,
			mr_sizeHeight,
			mr_exPrice,
			mr_tax,
			mr_changed
		);
	
		return "redirect:/cargo/export/list.action";
	}
}
