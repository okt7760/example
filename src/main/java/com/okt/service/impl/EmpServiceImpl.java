package com.okt.service.impl;

import com.okt.dao.EmpolyeeDao;
import com.okt.entity.Empolyee;
import com.okt.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by obeeey on 2021/3/22
 * 即使再小的帆也能远航
 */
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    EmpolyeeDao dao;

    @Override
    public Empolyee findOneEmp(String f_username) {

        Empolyee oneEmp = dao.findOneEmp(f_username);
        return oneEmp;

    }

    @Override
    public void updatePassword(String newpwd, String username) {
        dao.updateEmpPassword(newpwd,username);
    }
}
