package com.xsh.crm.workbench.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
    @RequestMapping("/save")
    public String save(){
        return "workbench/transaction/save";
    }
    @RequestMapping("/detail")
    public String detail(){
        return "workbench/transaction/detail";
    }
}
