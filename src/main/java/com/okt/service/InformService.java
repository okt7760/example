package com.okt.service;

import com.okt.entity.Inform;

import java.util.List;

/**
 * Create by obeeey on 2021/3/21
 * 即使再小的帆也能远航
 */
public interface InformService {

    List<Inform> findAllInform(int role);

    Inform informDetail(String inf_id);

    void insertInform(String title, String context, String writer, int mode);

    void delInformById(String id);

    List<Inform> findAllInformById(String f_id);

    List<Inform> adminFindAllInform();

}
