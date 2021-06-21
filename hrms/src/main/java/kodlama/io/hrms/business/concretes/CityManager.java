package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.CityService;
import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.ErrorDataResult;
import kodlama.io.hrms.core.utilities.results.SuccessDataResult;
import kodlama.io.hrms.dataAccess.abstracts.CityDao;
import kodlama.io.hrms.entities.concretes.City;

@Service
public class CityManager implements CityService{
	private CityDao cityDao;

    @Autowired
    public CityManager(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public DataResult<List<City>> getAll() {
        return new SuccessDataResult<List<City>>(this.cityDao.findAll(), "Cities listed!");
    }

    @Override
    public DataResult<City> getById(int id) {
        City city = this.cityDao.findById(id);
        if (city == null)
            return new ErrorDataResult<City>();
        return new SuccessDataResult<City>(city);
    }
}