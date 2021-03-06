package com.xsh.crm.workbench.controller;

import com.xsh.crm.settings.dao.StudentMapper;
import com.xsh.crm.settings.domain.DicValue;
import com.xsh.crm.settings.domain.Student;
import com.xsh.crm.utils.DateTimeUtil;
import com.xsh.crm.utils.UUIDUtil;
import com.xsh.crm.workbench.dao.ActivityMapper;
import com.xsh.crm.workbench.dao.ClueActivityRelationMapper;
import com.xsh.crm.workbench.dao.ClueMapper;
import com.xsh.crm.workbench.dao.ContactsActivityRelationMapper;
import com.xsh.crm.workbench.domain.*;
import com.xsh.crm.workbench.service.ClueService;
import com.xsh.crm.workbench.service.ContactService;
import com.xsh.crm.workbench.service.CustomerService;
import com.xsh.crm.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/clue")
public class ClueController {
    @Autowired
    private ClueService clueService;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private TranService tranService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;
    @Autowired
    private ContactsActivityRelationMapper contactsActivityRelationMapper;
    @RequestMapping("/selectOwner")
    @ResponseBody
    public String selectOwner(String owner){
        Student student = studentMapper.selectByPrimaryKey(owner);
        return student.getName();
    }
    /**
     * ????????????
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(String[] id){
        int i = clueService.deleteCluesById(id);
        return "??????"+i+"???????????????!";
    }
    @RequestMapping("/pageMsg")
    @ResponseBody
    public List<Clue> pageMsg(String pageNo,String pageSize){
        //??????limit????????????
        int s = Integer.parseInt(pageSize);
        int n = (Integer.parseInt(pageNo)-1)*s;

        List<Clue> clueToPage = clueService.getClueToPage(n,s);
        return clueToPage;
    }
    /**
     * ???????????????
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public String page(){
       return clueService.getAllClueCount()+"";
    }
    /**
     * ????????????
     * @param clue
     * @param request
     * @param nextContactTime
     * @return
     */
    @RequestMapping("/saveClue")
    @ResponseBody
    public String clueSaveClue(Clue clue,HttpServletRequest request,String nextContactTime ){
        String createtime = DateTimeUtil.getSysTime();
        clue.setCreatetime(createtime);
        clue.setId(UUIDUtil.getUUID());
        clue.setNextcontacttime(nextContactTime);
        Student student = (Student) request.getSession().getAttribute("student");
        clue.setCreateby(student.getName());
        int i = clueService.saveClue(clue);
        if (i>0) {
            return "true";
        }else {
            return "false";
        }
    }

    /**
     * ???????????????
     * @return
     */
    @RequestMapping("/dic")
    @ResponseBody
    public List<Student> students(){
        List<Student> allStudents = clueService.getAllStudents();
        return allStudents;
    }
    /**
     * ??????????????????????????????
     * @param model
     * @param servletRequest
     * @return
     */
    @RequestMapping("/index")
    public String clueIndex(Model model, HttpServletRequest servletRequest){
        ServletContext servletContext = servletRequest.getServletContext();
        List<DicValue> appellationList = (List<DicValue>) servletContext.getAttribute("appellationList");
        List<DicValue> clueStateList = (List<DicValue>) servletContext.getAttribute("clueStateList");
        List<DicValue> sourceList = (List<DicValue>) servletContext.getAttribute("sourceList");
        model.addAttribute("appellationList",appellationList);
        model.addAttribute("clueStateList",clueStateList);
        model.addAttribute("sourceList",sourceList);

        return "workbench/clue/index";
    }

    /**
     * detail??????????????????
     * @param request
     * @return
     */
    @RequestMapping("/detail")
    public String detail(HttpServletRequest request){
        HttpSession session = request.getSession();
        //??????????????????
        String id = request.getParameter("id");
        Clue clueBean = clueService.getClueBean(id);
        Student student = studentMapper.selectByPrimaryKey(clueBean.getOwner());
            session.setAttribute("clueOwner",student.getName());
            session.setAttribute("clueBean",clueBean);
        //???????????????????????????
        request.setAttribute("clueOwner",student.getName());
        request.setAttribute("clueBean",clueBean);
        Activity[] activities = clueService.activityAndClue(id);
        session.setAttribute("activities",activities);
        request.setAttribute("activities",activities);


        ArrayList<Activity> activity = (ArrayList<Activity>) clueService.selectActivities(clueBean.getId());
        System.out.println(activity);
        request.setAttribute("act",activity);
        return "workbench/clue/detail";
    }

    /**
     * ?????????????????????????????????????????????
     * @param request
     * @return
     */
    @RequestMapping("/convert")
    public String  convert(HttpServletRequest request) {
        clueConvert(request);
        return "workbench/clue/convert";
    }

    /**
     * ????????????
     * @param clueId
     * @param activityId
     * @param request
     * @return
     */
    @RequestMapping("/removeClue")
    public String removeClue(String clueId,String activityId,HttpServletRequest request){
        int remove = clueService.remove(clueId,activityId);
        HttpSession session = request.getSession();
        request.setAttribute("clueOwner",session.getAttribute("clueOwner"));
        request.setAttribute("clueBean",session.getAttribute("clueBean"));
        request.setAttribute("activities",session.getAttribute("activities"));
        Clue clueBean = (Clue) session.getAttribute("clueBean");


        return "forward:/clue/detail?id="+clueBean.getId();
    }

    /**
     * ?????????????????????
     *
     * @param id
     * @param request
     * @return
     */
        @RequestMapping("/concatActivity")
        @ResponseBody
        public String concatActivity(String[] id,HttpServletRequest request){
            Clue clueBean = (Clue) request.getSession().getAttribute("clueBean");
            for (String acid : id) {
               clueService.addActivityRelation(clueBean.getId(), acid);
            }
            return "true";
        }

    @RequestMapping("/clueConvert")
    @ResponseBody
    public String clueConvert(HttpServletRequest httpServletRequest){
        ArrayList<Activity> activities = activityMapper.clueConvert();
        httpServletRequest.getSession().setAttribute("clueConvertActivities",activities);
        return "true";
    }
    /**
     * ?????????????????????
     */
    @PostMapping("/convertTrue")
    public String convertTrue(HttpServletRequest request,Tran tran,String clueId,String flag){
        Clue clueBean = (Clue) request.getSession().getAttribute("clueBean");
        Student user= (Student) request.getSession().getAttribute("student");

        if (flag.equals("a")){
            //??????????????????
            tran.setId(UUIDUtil.getUUID());
            tran.setCreateby(user.getName());
            tran.setCreatetime(DateTimeUtil.getSysTime());
            String owner = clueBean.getOwner();
            tran.setOwner(owner);
            tranService.clueConvertToTran(tran);//????????????


        }
        String customerid =clueBean.getId();
        //????????????
        Customer customer = new Customer();
        customer.setId(clueBean.getId());
        customer.setOwner(clueBean.getOwner());
        customer.setName(clueBean.getCompany());
        customer.setPhone(clueBean.getPhone());
        customer.setCreateby(user.getName());
        customer.setNextcontacttime(clueBean.getNextcontacttime());
        customer.setDescription(clueBean.getDescription());
        customer.setAddress(clueBean.getAddress());
        customer.setCreatetime(DateTimeUtil.getSysTime());
        customer.setContactsummary(clueBean.getContactsummary());
        customer.setWebsite(clueBean.getWebsite());
        customerService.addCustomer(customer);

        //???????????????
        Contacts contacts = new Contacts();
        contacts.setAppellation(clueBean.getAppellation());
        contacts.setAddress(clueBean.getAddress());
        contacts.setMphone(clueBean.getMphone());
        contacts.setOwner(clueBean.getOwner());
        contacts.setSource(clueBean.getSource());
        contacts.setCustomerid(clueBean.getId());
        contacts.setCreateby(user.getName());
        contacts.setJob(clueBean.getJob());
        contacts.setCreatetime(DateTimeUtil.getSysTime());
        contacts.setEmail(clueBean.getEmail());
        String contactuuid = UUIDUtil.getUUID();
        contacts.setId(contactuuid);
        System.out.println(contactuuid);
        System.out.println(clueBean.getJob());
        contacts.setFullname(clueBean.getFullname());
        contacts.setNextcontacttime(customer.getNextcontacttime());
        contactService.addContact(contacts);
        //????????????
        List<ClueActivityRelation> clueActivityRelations = clueActivityRelationMapper.selectByByCuleId(clueBean.getId());
        for (ClueActivityRelation clueActivityRelation : clueActivityRelations) {
            ContactsActivityRelation contactsActivityRelation = new ContactsActivityRelation();
            contactsActivityRelation.setActivityid(clueActivityRelation.getActivityid());
            contactsActivityRelation.setContactsid(contactuuid);
            contactsActivityRelation.setId(UUIDUtil.getUUID());
            contactsActivityRelationMapper.insertSelective(contactsActivityRelation);
        }
        //????????????????????????
        ContactsCustomerRelation contactsCustomerRelation = new ContactsCustomerRelation();
        contactsCustomerRelation.setId(UUIDUtil.getUUID());
        contactsCustomerRelation.setContactsId(contactuuid);
        contactsCustomerRelation.setCustomerId(customerid);
        contactService.addContactsCustomerRelation(contactsCustomerRelation);
        clueMapper.deleteByPrimaryKey(clueBean.getId());
        clueActivityRelationMapper.deleteByCuleId(clueBean.getId());
        return "workbench/clue/index";
    }
}
