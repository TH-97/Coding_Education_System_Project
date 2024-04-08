package com.project.geomin.service;

import com.project.geomin.command.WorkVO;

import java.util.ArrayList;

public interface WorkService {
    public ArrayList<WorkVO> getList(String u_no); //select
}
