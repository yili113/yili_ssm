package cqupt.ssm.service.impl;

import cqupt.ssm.dao.IProductDao;
import cqupt.ssm.domain.Product;
import cqupt.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Liyi
 * @create 2019-10-04 9:12
 */
@Service
@Transactional
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll() throws Exception {

        return  productDao.findAll();

    }

    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }
}
