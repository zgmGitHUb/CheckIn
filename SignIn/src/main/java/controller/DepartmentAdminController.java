package controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.TbApplication;
import pojo.TbEmployee;
import service.departmentAdminService.DepartmentAdminService;

import java.util.List;

/**
 * Created by user on 2017/3/26.
 */
@Controller
@RequestMapping("/DepartmentAdmin")
public class DepartmentAdminController {
    @Autowired
    private DepartmentAdminService departmentAdminService;

    @RequestMapping("/queryEmployee")
    public @ResponseBody List<TbEmployee> queryEmployee(TbEmployee tbEmployee)
    {
        String departmentId=tbEmployee.getDepartmentid();
        if(departmentId==null||departmentId=="")
            return null;
        List<TbEmployee> employeeList=departmentAdminService.queryEmployee(tbEmployee);

        return employeeList;
    }

    @RequestMapping("/queryApplications")
    public @ResponseBody List<TbApplication> queryApplications(TbEmployee tbEmployee)
    {
        String departmentId=tbEmployee.getDepartmentid();
        if(departmentId==null||departmentId=="")
            return null;
        List<TbApplication> applications=departmentAdminService.queryApplication(tbEmployee);
        return applications;
    }
}
