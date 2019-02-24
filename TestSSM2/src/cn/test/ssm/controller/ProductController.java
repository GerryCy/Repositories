package cn.test.ssm.controller;

import cn.test.ssm.po.Product;
import cn.test.ssm.po.ProductExtend;
import cn.test.ssm.po.ProductQueryVo;
import cn.test.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
//@RequestMapping("/test")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/queryList.do")
    public ModelAndView queryList(ProductQueryVo productQueryVo) throws Exception{
        //从service层调用方法
        List<ProductExtend> productExtendList = productService.findProductListByName(productQueryVo);
        System.out.println(productExtendList);
        //返回ModelandView
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(productExtendList);
        modelAndView.setViewName("/WEB-INF/items/itemsList.jsp");
        return modelAndView;
    }

    @RequestMapping("/queryInfo.do")
    public ModelAndView queryInfo(@RequestParam(value = "id", required = true, defaultValue = "1") Integer testId) throws Exception {

        ProductExtend productExtend = productService.queryProductInfo(testId);
        System.out.println("查询详细信息："+productExtend);
        productExtend.setPid(testId);
        productExtend.setDesc("这是相机");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(productExtend);
        modelAndView.setViewName("/WEB-INF/items/editItem.jsp");
        return modelAndView;
    }

    @RequestMapping("/printInfo.action")
    public String printInfo(Product product, Date createtime) throws Exception {
        System.out.println("输出信息"+product);
        System.out.println(createtime);
        return "forward:queryList.do";
    }
}
