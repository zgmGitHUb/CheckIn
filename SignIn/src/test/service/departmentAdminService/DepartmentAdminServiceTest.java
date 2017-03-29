package service.departmentAdminService;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.TbApplication;
import pojo.TbDepartmentschedule;
import pojo.TbEmployee;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by user on 2017/3/25.
 */
public class DepartmentAdminServiceTest {

    private ApplicationContext applicationContext;
    @Before
    public void setUp() throws Exception {
        applicationContext=new ClassPathXmlApplicationContext(new String[]{"spring/applicationContext-Dao.xml","spring/applicationContext-Service.xml","spring/applicationContext-transaction.xml"});
    }


    @Test
    public void testQueryEmployee() throws Exception {
        DepartmentAdminService departmentAdminService= (DepartmentAdminService) applicationContext.getBean("departmentAdminService");
        TbEmployee tbEmployee=new TbEmployee();
        tbEmployee.setDepartmentid("1");
        List<TbEmployee> employeeList=departmentAdminService.queryEmployee(tbEmployee);

    }

    @Test
    public void testQueryApplication() throws Exception {
        DepartmentAdminService departmentAdminService= (DepartmentAdminService) applicationContext.getBean("departmentAdminService");
        TbEmployee tbEmployee=new TbEmployee();
        tbEmployee.setDepartmentid("1");
        List<TbApplication> applications=departmentAdminService.queryApplication(tbEmployee);
    }

    @Test
    public void testQueryHistoryApplication() throws Exception {
        DepartmentAdminService departmentAdminService= (DepartmentAdminService) applicationContext.getBean("departmentAdminService");
        TbEmployee tbEmployee=new TbEmployee();
        tbEmployee.setDepartmentid("1");
        List<TbApplication> applications=departmentAdminService.queryHistoryApplication(tbEmployee);
    }

    @Test
    public void testQueryDepartmentSchedule() throws Exception {
        DepartmentAdminService departmentAdminService= (DepartmentAdminService) applicationContext.getBean("departmentAdminService");
        TbEmployee tbEmployee=new TbEmployee();
        tbEmployee.setDepartmentid("1");
        List<TbDepartmentschedule> departmentschedules=departmentAdminService.queryDepartmentSchedule(tbEmployee);
    }
}