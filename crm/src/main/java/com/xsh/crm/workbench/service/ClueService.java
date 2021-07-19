package com.xsh.crm.workbench.service;

import com.xsh.crm.settings.domain.Student;
import com.xsh.crm.workbench.domain.Clue;

import java.util.List;

public interface ClueService {
    List<Student> getAllStudents();

    int saveClue(Clue clue);

    int getAllClueCount();

    List<Clue> getClueToPage(int n, int s);

    int deleteCluesById(String[] id);
}
