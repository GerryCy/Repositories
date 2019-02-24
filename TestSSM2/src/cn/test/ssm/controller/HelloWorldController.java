package cn.test.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HelloWorldController {

    @RequestMapping("/helloWorld.do")
    public ModelAndView helloWorld() throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("test","HelloSSM");
        modelAndView.setViewName("/WEB-INF/items/hello.jsp");
        return modelAndView;
    }

    @RequestMapping("/test_void.do")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws Exception{
        request.setAttribute("test","返回值为void类型的测试");
        request.getRequestDispatcher("/WEB-INF/items/hello.jsp").forward(request,response);
//        response.sendRedirect("/WEB-INF/items/hello.jsp");
    }

    @RequestMapping("/testString.do")
    public String testString(Model model) throws Exception {
        //其他进行的操作
        //通过形参model将数据返回到请求页面上   类似于返回ModelAndView中的addObject方法
        model.addAttribute("testString","testString");
        //然后返回逻辑视图名，经过视图解析器解析为相应的jsp等路径
        return "test/helloWorld";
    }
}
