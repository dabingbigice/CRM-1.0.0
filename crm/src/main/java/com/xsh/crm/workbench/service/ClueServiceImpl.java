package com.xsh.crm.workbench.service;

import com.xsh.crm.settings.dao.StudentMapper;
import com.xsh.crm.settings.domain.Student;
import com.xsh.crm.workbench.dao.ActivityMapper;
import com.xsh.crm.workbench.dao.ClueActivityRelationMapper;
import com.xsh.crm.workbench.dao.ClueMapper;
import com.xsh.crm.workbench.domain.Activity;
import com.xsh.crm.workbench.domain.Clue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class ClueServiceImpl implements ClueService {
    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public List<Student> getAllStudents() {
        List<Student> students = activityMapper.selectNames();
        return students;
    }

    @Override
    @Transactional
    public int saveClue(Clue clue) {
        int i = clueMapper.insertSelective(clue);
        return i;
    }

    @Override
    public int getAllClueCount() {

         return clueMapper.getAllClueCount();
    }

    @Override
    public List<Clue> getClueToPage(int n, int s) {

        return   clueMapper.getClueToPage(n,s);
    }

    @Override
    public int deleteCluesById(String[] id) {
        int j=0;
        for (int i = 0; i < id.length; i++) {
             j+= clueMapper.deleteByPrimaryKey(id[i]);
        }
        return j;
    }

    @Override
    public Clue getClueBean(String id) {
        Clue clue = clueMapper.selectByPrimaryKey(id);
        return clue;
    }

    @Override
    public Activity[] activityAndClue(String clueId) {
        System.out.println(clueId);
        String[] strings = clueActivityRelationMapper.selectActivityId(clueId);
        Activity[] activities=new Activity[strings.length];
        int i=0;
        for (String string : strings) {
            Activity activity = activityMapper.selectByPrimaryKey(string);
            Student student = studentMapper.selectByPrimaryKey(activity.getOwner());
            activity.setOwnerName(student.getName());
            activities[i++]=activity;

        }
        return activities;
    }

    @Override
    public int remove(String clueId,String activityId) {
        int remove = clueActivityRelationMapper.remove(clueId,activityId);
        return remove;
    }

    @Override
    public List<Activity> selectActivities(String clueId) {
        return activityMapper.selectActivities(clueId);

    }

    @Override
    public int addActivityRelation(String id, String acid) {
        clueActivityRelationMapper.addActivityRelation(id,acid);
        return 0;
    }

    @Override
    public ArrayList<Activity> clueConvert() {
        return null;
    }
}
