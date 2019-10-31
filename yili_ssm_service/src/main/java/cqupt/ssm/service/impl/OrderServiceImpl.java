package cqupt.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import cqupt.ssm.dao.IOrdersDao;
import cqupt.ssm.domain.Orders;
import cqupt.ssm.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Liyi
 * @create 2019-10-04 14:49
 */

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrdersDao ordersDao;
    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        // 需要在真正执行查询语句前进行操作  中间不能被分割
        // param1:起始页码  param2:每页所含条数
        PageHelper.startPage(page, size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String ordersId) throws Exception {
        return ordersDao.findById(ordersId);
    }
}
