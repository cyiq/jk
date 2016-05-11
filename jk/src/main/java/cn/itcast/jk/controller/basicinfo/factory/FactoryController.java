package cn.itcast.jk.controller.basicinfo.factory;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.dao.FactoryDao;
import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.service.FactoryService;

@Controller
public class FactoryController extends BaseController{
	@Resource
	FactoryService factoryService;
	//厂家列表显示
	@RequestMapping("/basicinfo/factory/list.action")
	public String list(Model model){
		List<Factory> dataList = factoryService.find(null);
		model.addAttribute("dataList", dataList);
		return "/basicinfo/factory/jFactoryList.jsp";
	}
	//转向新增厂家页面
	@RequestMapping("/basicinfo/factory/tocreate.action")
	public String toCreate(){
		return "/basicinfo/factory/jFactoryCreate.jsp";
	}
	//新增保存
	@RequestMapping("/basicinfo/factory/insert.action")
	public String insert(Factory factory){
		factoryService.insert(factory);
		return "redirect:/basicinfo/factory/list.action";	//保存完成后转向列表action
	}
	//转向修改页面
	@RequestMapping("/basicinfo/factory/toupdate.action")
	public String toUpdate(String id , Model model){
		Factory obj = factoryService.get(id);
		model.addAttribute("obj", obj);
		return "/basicinfo/factory/jFactoryUpdate.jsp";
	}
	//修改保存
	@RequestMapping("/basicinfo/factory/update.action")
	public String update(Factory factory){
		factoryService.update(factory);
		return "redirect:/basicinfo/factory/list.action";	//保存完成后转向列表action
	}
	
	//删除
	@RequestMapping("/basicinfo/factory/deleteById.action")
	public String deleteById(String id){
		factoryService.deleteById(id);
		return "redirect:/basicinfo/factory/list.action";	//删除完成后转向列表action
	}
	//删除多条
	@RequestMapping("/basicinfo/factory/delete.action")
	//public String delete(@RequestParam("id")String[] ids)	将页面中id属性的值自动封装到ids数组中
	public String delete(String id){
		factoryService.delete(id.split(","));
		return "redirect:/basicinfo/factory/list.action";	//删除完成后转向列表action
	}
	//查看
	@RequestMapping("/basicinfo/factory/toview.action")
	public String toView(String id,Model model){
		Factory obj = factoryService.get(id);
		model.addAttribute("obj", obj);
		return "/basicinfo/factory/jFactoryView.jsp";
	}
	
	@RequestMapping("/basicinfo/factory/start.action")
	public String start(@RequestParam("id")String[] ids){
		factoryService.start(ids);
		return "redirect:/basicinfo/factory/list.action";	//完成后转向列表action
	}
	
	@RequestMapping("/basicinfo/factory/stop.action")
	public String stop(@RequestParam("id")String[] ids){
		factoryService.stop(ids);
		return "redirect:/basicinfo/factory/list.action";	//完成后转向列表action
	}
}
