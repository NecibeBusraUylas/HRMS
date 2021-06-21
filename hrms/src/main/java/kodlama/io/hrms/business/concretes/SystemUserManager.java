package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.SystemUserService;
import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.SuccessDataResult;
import kodlama.io.hrms.dataAccess.abstracts.SystemUserDao;
import kodlama.io.hrms.entities.concretes.SystemUser;

@Service
public class SystemUserManager implements SystemUserService{
	private SystemUserDao systemUserDao;

    @Autowired
    public SystemUserManager(SystemUserDao systemUserDao) {
        this.systemUserDao = systemUserDao;
    }

    @Override
    public DataResult<List<SystemUser>> getAll() {
        return new SuccessDataResult<List<SystemUser>>(this.systemUserDao.findAll(), "System users listed!");
    }
}