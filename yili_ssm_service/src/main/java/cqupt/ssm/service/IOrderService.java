package cqupt.ssm.service;

import cqupt.ssm.domain.Orders;

import java.util.List;

/**
 * @author Liyi
 * @create 2019-10-04 14:48
 */
public interface IOrderService {
    public List<Orders> findAll(int page, int size) throws  Exception;

    Orders findById(String ordersId) throws  Exception;
}
