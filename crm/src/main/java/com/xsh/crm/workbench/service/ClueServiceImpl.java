package com.xsh.crm.workbench.service;

import com.xsh.crm.settings.dao.StudentMapper;
import com.xsh.crm.settings.domain.Student;
import com.xsh.crm.workbench.dao.ActivityMapper;
import com.xsh.crm.workbench.dao.ClueMapper;
import com.xsh.crm.workbench.domain.Clue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ClueServiceImpl implements ClueService {
    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private ActivityMapper activityMapper;
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
}
