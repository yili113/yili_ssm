package cqupt.ssm.service;

import cqupt.ssm.domain.Role;
import cqupt.ssm.domain.Permission;

import java.util.List;

/**
 * @author Liyi
 * @create 2019-10-27 9:03
 */
public interface IRoleService {
    public List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;

    Role findById(String roleId) throws  Exception;

    List<Permission> findOtherPermissions(String roleId) throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;

}
