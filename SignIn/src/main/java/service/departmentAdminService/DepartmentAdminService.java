package service.departmentAdminService;

import mapper.TbEmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.TbEmployee;
import pojo.TbEmployeeExample;

import java.util.List;

/**
 * Created by user on 2017/3/25.
 */
@Service
public class DepartmentAdminService {

    @Autowired
    private TbEmployeeMapper tbEmployeeMapper;

    public List<TbEmployee>  queryEmployee(TbEmployee tbEmployee)
    {
        TbEmployeeExample employeeExample=new TbEmployeeExample();
        TbEmployeeExample.Criteria criteria=employeeExample.createCriteria();
        criteria.andDepartmentidEqualTo(tbEmployee.getDepartmentid());
        List<TbEmployee> employeeList=tbEmployeeMapper.selectByExample(employeeExample);
        return employeeList;
    }
}
