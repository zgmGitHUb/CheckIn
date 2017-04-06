package controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import otherFunction.binding.bindEmail;
import otherFunction.binding.bindTelephone;
import pojo.TbCompanyrepresentative;
import pojo.TbEmployee;
import service.adminAccountManagementService.CompanyAdminAccountManagementService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Created by ZGM on 2017/4/6.
 */
@Controller
@RequestMapping("/CompanyAdminAccountManagement")
public class CompanyAdminAccountManagementController {

    @Autowired
    private CompanyAdminAccountManagementService companyAdminAccountManagementService;

    //查看手机号码和邮箱
    @RequestMapping("/showTelephoneAndEmail")
    public @ResponseBody
    List<TbCompanyrepresentative> queryEmployee(TbCompanyrepresentative tbCompanyrepresentative)
    {
        String employeeId=tbCompanyrepresentative.getCompanyrepresentativeid();
        if(employeeId==null||employeeId=="")
            return null;
        List<TbCompanyrepresentative> companyrepresentativeList= companyAdminAccountManagementService.queryEmployee(tbCompanyrepresentative);

        return companyrepresentativeList;
    }

    //发送手机短信验证码
    @RequestMapping("/sendMessage")
    public @ResponseBody
    JSONObject sendMessage(TbCompanyrepresentative tbCompanyrepresentative,HttpServletRequest request)throws IOException
    {   System.out.println("test sendMessage Tel:"+tbCompanyrepresentative.getTelephone());
        JSONObject json = new JSONObject();
        try {
             int randomNumber = bindTelephone.sendMessage(tbCompanyrepresentative.getTelephone());
            //int randomNumber=123;//测试时用 不用发短信
            if(randomNumber!=0) {
                //将验证码写入Session
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(1 * 60);//Session1分钟失效
                session.setAttribute("Verification", randomNumber);
                session.setAttribute("EmployeeId", tbCompanyrepresentative.getCompanyrepresentativeid());
                json.put("status", true);
                System.out.println(session.getAttribute("EmployeeId"));
                System.out.println(session.getAttribute("Verification"));
            }
            else  json.put("status",false);
        }
        catch (Exception e) {
            json.put("status",false);
        }
        return json;
    }

    //绑定手机
    @RequestMapping("/bindTelephone")
    public @ResponseBody JSONObject bindTelephone(TbCompanyrepresentative tbCompanyrepresentative,HttpServletRequest request)
    {   System.out.println("test bindTelephone");
        JSONObject json = new JSONObject();
        try {
            String code = request.getParameter("code");
            String employeeId=tbCompanyrepresentative.getCompanyrepresentativeid();
//        String tel=tbEmployee.getTelephone();
//            System.out.println(tel);
//            System.out.println(code);
//            System.out.println(employeeId+" test code eid");
            HttpSession session = request.getSession();
//            System.out.println("test set session:"+session.getAttribute("EmployeeId"));
//            System.out.println(session.getAttribute("Verification"));
            int code2=(int) Integer.parseInt(code);//String转int
            //验证id和验证码是否都正确
            if(session.getAttribute("Verification").equals(code2)&& session.getAttribute("EmployeeId").equals(employeeId)) {
                if (companyAdminAccountManagementService.changeTelephone(tbCompanyrepresentative))json.put("status",true);
                else json.put("status",false);
            }
            else json.put("status",false);
        }
        catch (Exception e) {
            json.put("status",false);
        }
        return json;
    }

    //发送邮箱验证链接
    @RequestMapping("/sendEmailCode")
    public @ResponseBody JSONObject sendEmailCode(TbCompanyrepresentative tbCompanyrepresentative,HttpServletRequest request)throws IOException
    {   System.out.println("test sendEmailCode Email:"+tbCompanyrepresentative.getEmail());
        JSONObject json = new JSONObject();
        try {
            String UUID = bindEmail.sendEmailCode(tbCompanyrepresentative.getCompanyrepresentativeid(),tbCompanyrepresentative.getEmail(),"CompanyAdminAccountManagement");
            // String UUID2="123";//测试时用 不用发邮件
            if(UUID!=null) {
                //将UUID写入Session
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(10 * 60);//Session 10分钟失效
                session.setAttribute("UUID", UUID);
                session.setAttribute("CompanyrepresentativeId", tbCompanyrepresentative.getCompanyrepresentativeid());
                session.setAttribute("Email",tbCompanyrepresentative.getEmail());
                json.put("status", true);
                System.out.println(session.getAttribute("CompanyrepresentativeId"));
                System.out.println(session.getAttribute("UUID"));
                System.out.println(session.getAttribute("Email"));
            }
            else  json.put("status",false);
        }
        catch (Exception e) {
            json.put("status",false);
        }
        return json;
    }

    @RequestMapping("/checkBingingEamilInformation")
    public @ResponseBody void checkEmailCode(TbCompanyrepresentative tbCompanyrepresentative,HttpServletRequest request,
                                             HttpServletResponse response)throws IOException{
        System.out.println("test run checkBingingEamilInformation");
        JSONObject json = new JSONObject();
        Writer writer = response.getWriter();
        String companyRepresentativeId = request.getParameter("Id");
        String email = request.getParameter("Email");
        String UUID = request.getParameter("UUID");
        String createTime = request.getParameter("CreateTime");
            System.out.println("test checkBingingEamilInformation Eid:"+companyRepresentativeId);
//            System.out.println("test UUID:"+UUID);
//            System.out.println("test email:"+email);
//        tbEmployee取不倒Eid的值，通过request域中从进来，再赋值给tbEmployee
        tbCompanyrepresentative.setCompanyrepresentativeid(companyRepresentativeId);
        //取出Session中的值
        HttpSession session = request.getSession();
        String sCompanyRepresentativeId= (String) session.getAttribute("CompanyrepresentativeId");
        String sEmail= (String) session.getAttribute("Email");
        String sUUID= (String) session.getAttribute("UUID");
//            System.out.println("test set session:"+sEmployeeId);
//            System.out.println(sEmail);
//            System.out.println(sUUID);

        if(sCompanyRepresentativeId.equals(companyRepresentativeId) && sEmail.equals(email) && sUUID.equals(UUID)) {
            if(bindEmail.checkEmailCode(createTime)) {
                if(companyAdminAccountManagementService.changeEmail(tbCompanyrepresentative))
                    writer.write("Success");
                else writer.write("Fail");
            }
            else writer.write("Fail");
        }
        else writer.write("Fail");
    }

    //管理员修改密码
    @RequestMapping("/changePassword")
    public @ResponseBody JSONObject queryApplications(TbCompanyrepresentative tbCompanyrepresentative)
    {   System.out.println("test changePassword");
        JSONObject json = new JSONObject();
        String password=tbCompanyrepresentative.getPassword();
        if(password==null||password=="") {
            json.put("status", false);
        }
        else {
            if (companyAdminAccountManagementService.changePassword(tbCompanyrepresentative))json.put("status",true);
            else json.put("status",false);
        }
        return json;
    }

    //管理员密码验证
    @RequestMapping("/verification")
    public @ResponseBody JSONObject verification(TbCompanyrepresentative tbCompanyrepresentative)
    {   System.out.println("test verification");
        JSONObject json = new JSONObject();
        String password=tbCompanyrepresentative.getPassword();
        if(password==null||password=="") {
            json.put("status", false);
        }
        else {
            List<TbCompanyrepresentative> tbCompanyrepresentativeList = companyAdminAccountManagementService.verification(tbCompanyrepresentative);
            TbCompanyrepresentative tbCompanyrepresentative2 = tbCompanyrepresentativeList.get(0);
            System.out.println(password);
            System.out.println(tbCompanyrepresentative2.getPassword()+" tbEmployee2.getPassword()");
            if (password .equals(tbCompanyrepresentative2.getPassword()) )json.put("status", true);
            else json.put("status", false);
        }
        return json;
    }

}


