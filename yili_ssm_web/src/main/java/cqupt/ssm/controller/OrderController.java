package cqupt.ssm.controller;

import com.github.pagehelper.PageInfo;
import cqupt.ssm.domain.Orders;
import cqupt.ssm.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

/**
 * @author Liyi
 * @create 2019-10-04 14:51
 */
@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IOrderService orderService;

/*    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<Orders> ordersList = orderService.findAll();
        modelAndView.addObject("ordersList", ordersList);
        modelAndView.setViewName("orders-list");
        return modelAndView;

    }*/
    @RequestMapping("/findAll.do")
    public  ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "4") int size) throws  Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<Orders> ordersList = orderService.findAll(page, size);
        // PageInfo就是一个分页Bean
        // pageInfo不仅包含了分页相关信息 如当前页 总页数等  还包含了每页显示的数据内容
        PageInfo pageInfo = new PageInfo(ordersList);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("orders-page-list");
        return  modelAndView;

    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String ordersId) throws  Exception {
        ModelAndView modelAndView = new ModelAndView();
        Orders orders = orderService.findById(ordersId);
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("orders-show");
        return modelAndView;
    }
}
