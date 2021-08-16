package com.xsh.crm.workbench.controller;

import com.xsh.crm.settings.dao.StudentMapper;
import com.xsh.crm.settings.domain.DicValue;
import com.xsh.crm.settings.domain.Student;
import com.xsh.crm.utils.DateTimeUtil;
import com.xsh.crm.utils.UUIDUtil;
import com.xsh.crm.workbench.domain.Activity;
import com.xsh.crm.workbench.domain.Clue;
import com.xsh.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @RequestMapping("/selectOwner")
    @ResponseBody
    public String selectOwner(String owner){
        Student student = studentMapper.selectByPrimaryKey(owner);
        return student.getName();
    }
    /**
     * 删除线索
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(String[] id){
        int i = clueService.deleteCluesById(id);
        return "删除"+i+"条记录成功!";
    }
    @RequestMapping("/pageMsg")
    @ResponseBody
    public List<Clue> pageMsg(String pageNo,String pageSize){
        //获取limit分页参数
        int s = Integer.parseInt(pageSize);
        int n = (Integer.parseInt(pageNo)-1)*s;

        List<Clue> clueToPage = clueService.getClueToPage(n,s);
        return clueToPage;
    }
    /**
     * 返回总条数
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public String page(){
       return clueService.getAllClueCount()+"";
    }
    /**
     * 存储线索
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
     * 获取用户名
     * @return
     */
    @RequestMapping("/dic")
    @ResponseBody
    public List<Student> students(){
        List<Student> allStudents = clueService.getAllStudents();
        return allStudents;
    }
    /**
     * 获取下拉框字典中的值
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
    @RequestMapping("/detail")
    public String detail(HttpServletRequest request){
        HttpSession session = request.getSession();
        //设置回显数据
        String id = request.getParameter("id");
        Clue clueBean = clueService.getClueBean(id);
        Student student = studentMapper.selectByPrimaryKey(clueBean.getOwner());
            session.setAttribute("clueOwner",student.getName());
            session.setAttribute("clueBean",clueBean);
        //设置市场关联的数据
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
    @RequestMapping("/convert")
    public String  convert() {
        return "workbench/clue/convert";
    }
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

        @RequestMapping("/concatActivity")
        @ResponseBody
        public String concatActivity(String[] id,HttpServletRequest request){
            Clue clueBean = (Clue) request.getSession().getAttribute("clueBean");
            for (String acid : id) {
               clueService.addActivityRelation(clueBean.getId(), acid);
            }
            return "true";
        }

}
