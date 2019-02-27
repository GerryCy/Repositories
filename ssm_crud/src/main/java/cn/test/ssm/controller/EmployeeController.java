package cn.test.ssm.controller;

import cn.test.ssm.bean.Employee;
import cn.test.ssm.service.EmployeeService;
import cn.test.ssm.utils.Message;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/emps")
    public ModelAndView getEmps(@RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum) throws Exception{
        //使用分页查询
        //使用pageHelper插件，在查询之前只需要调用（传入页码和条目数）
        PageHelper.startPage(pageNum, 8);
        //startPage后面紧跟的查询就是分页查询
        List<Employee> employeeList = employeeService.getAll();

        //使用PageInfo,PageInfo包含了非常全面的分页属性
        //将查询的结果信息放在pageInfo中，并且指定连续显示8页
        PageInfo pageInfo = new PageInfo(employeeList, 5);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("WEB-INF/views/list.jsp");
        //return "/WEB-INF/views/list.jsp";
        return modelAndView;
    }

//    @RequestMapping("/emps")
    //需要导入jackson包
//    @ResponseBody
    public Message getEmpsWithAjax(@RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum) throws Exception {
        PageHelper.startPage(pageNum, 8);
        List<Employee> employeeList = employeeService.getAll();
        PageInfo pageInfo = new PageInfo(employeeList, 5);
        return Message.success().updateData("pageInfo", pageInfo);
    }
}
