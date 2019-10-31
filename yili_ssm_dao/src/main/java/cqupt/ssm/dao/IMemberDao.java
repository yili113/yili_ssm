package cqupt.ssm.dao;

import cqupt.ssm.domain.Member;
import cqupt.ssm.domain.Product;
import org.apache.ibatis.annotations.Select;

/**
 * @author Liyi
 * @create 2019-10-05 9:30
 */
public interface IMemberDao {

    // 根据id查询产品 sql语句的参数要与方法中的传入参数一致
    @Select("select * from member where id=#{id}")
    public Member findById(String id) throws Exception;
}
