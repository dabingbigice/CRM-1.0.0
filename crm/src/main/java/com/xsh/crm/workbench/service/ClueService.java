package com.xsh.crm.workbench.service;

import com.xsh.crm.settings.domain.Student;
import com.xsh.crm.workbench.domain.Activity;
import com.xsh.crm.workbench.domain.Clue;

import java.util.ArrayList;
import java.util.List;

public interface ClueService {
    List<Student> getAllStudents();

    int saveClue(Clue clue);

    int getAllClueCount();

    List<Clue> getClueToPage(int n, int s);

    int deleteCluesById(String[] id);

    Clue getClueBean(String id);
    Activity[] activityAndClue(String clueId);

    int remove(String clueId,String activityId);

    List<Activity> selectActivities(String clueId);

    int addActivityRelation(String id, String acid);
    ArrayList<Activity> clueConvert();
}
