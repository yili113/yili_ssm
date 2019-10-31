package cqupt.ssm.service;

import cqupt.ssm.domain.Product;

import java.util.List;

/**
 * @author Liyi
 * @create 2019-10-04 9:12
 */
public interface IProductService {
    public List<Product> findAll() throws  Exception;

    void save(Product product) throws  Exception;
}
