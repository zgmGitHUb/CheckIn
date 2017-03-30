package service.departmentAdminService;

import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2017/3/25.
 */
@Service
public class DepartmentAdminService {

    @Autowired
    private TbEmployeeMapper tbEmployeeMapper;

    @Autowired
    private TbApplicationMapper tbApplicationMapper;

    @Autowired
    private TbDepartmentscheduleMapper tbDepartmentscheduleMapper;

    @Autowired
    private TbEmployeenotifyMapper tbEmployeenotifyMapper;

    @Autowired
    private TbNotifyMapper notifyMapper;

    @Autowired
    private  TbActivityMapper activityMapper;

    public List<TbEmployee>  queryEmployee(TbEmployee tbEmployee)
    {
        TbEmployeeExample employeeExample=new TbEmployeeExample();
        TbEmployeeExample.Criteria criteria=employeeExample.createCriteria();
        criteria.andDepartmentidEqualTo(tbEmployee.getDepartmentid());
        List<TbEmployee> employeeList=tbEmployeeMapper.selectByExample(employeeExample);
        for(TbEmployee tmp:employeeList)
        {
            //System.out.println(tmp);
            tmp.setPassword(null);
        }
        return employeeList;
    }
    public List<TbApplication> queryApplication(TbEmployee tbEmployee)
    {
        List<TbEmployee> employeeList=queryEmployee(tbEmployee);
        TbApplicationExample applicationExample=new TbApplicationExample();
        TbApplicationExample.Criteria criteria=applicationExample.createCriteria();
        List<String>employIdList=new LinkedList<String>();
        for(TbEmployee tmp:employeeList)
        {
            //System.out.println(tmp)
            employIdList.add(tmp.getEmployeeid());
        }
        criteria.andEmployeeidIn(employIdList);
        criteria.andStatusEqualTo(false);
        List<TbApplication> applications=tbApplicationMapper.selectByExample(applicationExample);
        return applications;
    }
    public List<TbApplication> queryHistoryApplication(TbEmployee tbEmployee)
    {
        List<TbEmployee> employeeList=queryEmployee(tbEmployee);
        TbApplicationExample applicationExample=new TbApplicationExample();
        TbApplicationExample.Criteria criteria=applicationExample.createCriteria();
        List<String>employIdList=new LinkedList<String>();
        for(TbEmployee tmp:employeeList)
        {
            //System.out.println(tmp)
            employIdList.add(tmp.getEmployeeid());
        }
        criteria.andEmployeeidIn(employIdList);
        criteria.andStatusEqualTo(true);

        Date d1=new Date();
        Calendar now =Calendar.getInstance();
        now.setTime(d1);
        now.set(Calendar.DATE,now.get(Calendar.DATE)-7);
        Date d2=now.getTime();

        criteria.andApplicationtimeBetween(d2, d1);
        List<TbApplication> applications=tbApplicationMapper.selectByExample(applicationExample);
        return applications;
    }

    public List<TbDepartmentschedule> queryDepartmentSchedule(TbEmployee tbEmployee)
    {
        TbDepartmentscheduleExample tbDepartmentscheduleExample=new TbDepartmentscheduleExample();
        TbDepartmentscheduleExample.Criteria criteria=tbDepartmentscheduleExample.createCriteria();
        criteria.andDepartmentidEqualTo(tbEmployee.getDepartmentid());
        List<TbDepartmentschedule> departmentschedules=tbDepartmentscheduleMapper.selectByExample(tbDepartmentscheduleExample);
        return departmentschedules;
    }
    public List<TbEmployeenotify> queryEmployeeNotify(TbEmployee employee,boolean status)
    {
        TbEmployeenotifyExample employeenotifyExample=new TbEmployeenotifyExample();
        TbEmployeenotifyExample.Criteria criteria=employeenotifyExample.createCriteria();
        criteria.andEmployeeidEqualTo(employee.getEmployeeid());
        criteria.andStatusEqualTo(status);
        List<TbEmployeenotify> employeenotifies=tbEmployeenotifyMapper.selectByExample(employeenotifyExample);
        return employeenotifies;
    }
    public List<TbNotify> queryNotify(TbEmployee employee,boolean status)
    {
        List<TbEmployeenotify> employeenotifies=queryEmployeeNotify(employee,status);
        List<String>notifyIds=new LinkedList<String>();
        for(TbEmployeenotify tmp:employeenotifies)
        {
            //System.out.println(tmp)
            notifyIds.add(tmp.getNotifyid());
        }

        TbNotifyExample notifyExample=new TbNotifyExample();
        TbNotifyExample.Criteria criteria=notifyExample.createCriteria();
        criteria.andNotifyidIn(notifyIds);
        if(status==true)
        {
            Date d1=new Date();
            Calendar now =Calendar.getInstance();
            now.setTime(d1);
            now.set(Calendar.DATE,now.get(Calendar.DATE)-7);
            Date d2=now.getTime();
            criteria.andNotifytimeBetween(d2,d1);
        }
        List<TbNotify> notifies=notifyMapper.selectByExample(notifyExample);
        return notifies;
    }

    public List<TbActivity> queryActivites(TbEmployee employee)
    {
        TbActivityExample activityExample=new TbActivityExample();
        TbActivityExample.Criteria criteria=activityExample.createCriteria();
        criteria.andEmployeeidEqualTo(employee.getEmployeeid());
        Date d1=new Date();
        Calendar now =Calendar.getInstance();
        now.setTime(d1);
        now.set(Calendar.DATE,now.get(Calendar.DATE)-7);
        Date d2=now.getTime();
        criteria.andStarttimeBetween(d2,d1);
        List<TbActivity> activities=activityMapper.selectByExample(activityExample);
        return activities;
    }
}
