package com.okt.service;

import com.okt.entity.Empolyee;

/**
 * Create by obeeey on 2021/3/22
 * 即使再小的帆也能远航
 */
public interface EmpService {
    /**
     * 根据员工id查找信息
     * @param f_id
     * @return
     */
    Empolyee findOneEmp(String f_id);

    void updatePassword(String newpwd, String username);
}
