package cqupt.ssm.service.impl;

import cqupt.ssm.dao.ISysLogDao;
import cqupt.ssm.domain.SysLog;
import cqupt.ssm.service.ISysLogService;
import cqupt.ssm.domain.SysLog;
import cqupt.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public List<SysLog> findAll() throws Exception {
        return sysLogDao.findAll();
    }

    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }
}
