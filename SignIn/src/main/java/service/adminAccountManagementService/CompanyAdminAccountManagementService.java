package service.adminAccountManagementService;

import mapper.TbCompanyrepresentativeMapper;
import mapper.TbEmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.TbCompanyrepresentative;
import pojo.TbCompanyrepresentativeExample;
import pojo.TbEmployee;
import pojo.TbEmployeeExample;

import java.util.List;

/**
 * Created by ZGM on 2017/4/6.
 */
@Service
public class CompanyAdminAccountManagementService {

    @Autowired
    private TbCompanyrepresentativeMapper tbCompanyrepresentativeMapper;

    //验证旧密码是否正确
    public List<TbCompanyrepresentative> verification(TbCompanyrepresentative tbCompanyrepresentative)
    {
        TbCompanyrepresentativeExample tbCompanyrepresentativeExample=new TbCompanyrepresentativeExample();
        TbCompanyrepresentativeExample.Criteria criteria=tbCompanyrepresentativeExample.createCriteria();
        criteria.andCompanyrepresentativeidEqualTo(tbCompanyrepresentative.getCompanyrepresentativeid());
        List<TbCompanyrepresentative> tbCompanyrepresentativeList=tbCompanyrepresentativeMapper.selectByExample(tbCompanyrepresentativeExample);
//        for(TbEmployee tmp:employeeList)
//        {
//            tmp.setPassword(null);
//        }
        return tbCompanyrepresentativeList;
    }

    //修改密码
    public boolean changePassword(TbCompanyrepresentative tbCompanyrepresentative)
    {
        try {
            TbCompanyrepresentative tbCompanyrepresentative2 = tbCompanyrepresentativeMapper.selectByPrimaryKey(tbCompanyrepresentative.getCompanyrepresentativeid());
            tbCompanyrepresentative2.setPassword(tbCompanyrepresentative.getPassword());
            tbCompanyrepresentativeMapper.updateByPrimaryKey(tbCompanyrepresentative2);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    //修改手机号码
    public boolean changeTelephone(TbCompanyrepresentative tbCompanyrepresentative)
    {
        try {
            TbCompanyrepresentative tbCompanyrepresentative2 = tbCompanyrepresentativeMapper.selectByPrimaryKey(tbCompanyrepresentative.getCompanyrepresentativeid());
            tbCompanyrepresentative2.setTelephone(tbCompanyrepresentative.getTelephone());
            tbCompanyrepresentativeMapper.updateByPrimaryKey(tbCompanyrepresentative2);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    //修改邮箱
    public boolean changeEmail(TbCompanyrepresentative tbCompanyrepresentative)
    {
        System.out.println("test change Email");
        System.out.println(tbCompanyrepresentative.getCompanyrepresentativeid());
        System.out.println(tbCompanyrepresentative.getEmail());
        try {
            TbCompanyrepresentative tbCompanyrepresentative2 = tbCompanyrepresentativeMapper.selectByPrimaryKey(tbCompanyrepresentative.getCompanyrepresentativeid());
            tbCompanyrepresentative2.setEmail(tbCompanyrepresentative.getEmail());
            tbCompanyrepresentativeMapper.updateByPrimaryKey(tbCompanyrepresentative2);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    //查询手机号码及邮箱
    public List<TbCompanyrepresentative>  queryEmployee(TbCompanyrepresentative tbCompanyrepresentative)
    {
        TbCompanyrepresentativeExample tbCompanyrepresentativeExample=new TbCompanyrepresentativeExample();
        TbCompanyrepresentativeExample.Criteria criteria=tbCompanyrepresentativeExample.createCriteria();
        criteria.andCompanyrepresentativeidEqualTo(tbCompanyrepresentative.getCompanyrepresentativeid());
        List<TbCompanyrepresentative> tbCompanyrepresentativeList=tbCompanyrepresentativeMapper.selectByExample(tbCompanyrepresentativeExample);
        for(TbCompanyrepresentative tmp:tbCompanyrepresentativeList)
        {
            //System.out.println(tmp);
            tmp.setPassword(null);
        }
        return tbCompanyrepresentativeList;
    }

}
