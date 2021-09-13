package com.okt.service.impl;

import com.github.pagehelper.PageHelper;
import com.okt.dao.EmpolyeeDao;
import com.okt.dao.InformDao;
import com.okt.entity.Inform;
import com.okt.service.InformService;
import com.okt.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

/**
 * Create by obeeey on 2021/3/21
 * 即使再小的帆也能远航
 */
@Service
public class InformServiceImpl implements InformService {

    @Autowired
    InformDao informDao;
    @Autowired
    EmpolyeeDao empolyeeDao;

    @Override
    public List<Inform> findAllInform(int role) {
        List<Inform> informs = informDao.findAllInform(role);
        for (Inform inform : informs) {
            inform.setDesc_date_str(DateFormat.dateToString(inform.getDesc_date(), "yyyy-MM-dd"));
            inform.setInf_writer_str(empolyeeDao.findOneEmp(inform.getInf_writer()).getF_name());
        }
        return  informs;
    }


    @Override
    public Inform informDetail(String inf_id) {
        return informDao.informDetail(inf_id);
    }

    @Override
    public void insertInform(String title, String context, String writer, int mode) {
        Date desc_date=new Date();
        informDao.insertInform(DateFormat.dateToString(desc_date, "yyyyMMddHHmmss"), title, context, writer,desc_date,mode);
    }

    @Override
    public void delInformById(String id) {
        informDao.delInformById(id);
    }

    @Override
    public List<Inform> findAllInformById(String f_id) {
        List<Inform> informs = informDao.findAllInformById(f_id);
        for (Inform inform : informs) {
            inform.setDesc_date_str(DateFormat.dateToString(inform.getDesc_date(), "yyyy-MM-dd"));
            inform.setInf_writer_str(empolyeeDao.findOneEmp(inform.getInf_writer()).getF_name());
        }
        return  informs;
    }

    @Override
    public List<Inform> adminFindAllInform() {
        List<Inform> allInform = informDao.findAllInformNoneRole();
        for (Inform inform : allInform) {
            inform.setDesc_date_str(DateFormat.dateToString(inform.getDesc_date(), "yyyy-MM-dd"));
        }
        return allInform;
    }


}
