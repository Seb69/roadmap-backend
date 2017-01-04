package io.roadmapp.service;

import io.roadmapp.dao.RoadMappDao;
import io.roadmapp.dao.RoadMappMarkersJoinTableDao;
import io.roadmapp.model.RoadMapp;
import io.roadmapp.model.RoadMappMarkersJoinTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roadmappMarkersService")
@Transactional
public class RoadMappMarkersJoinTableServiceImpl implements RoadMappMarkersJoinTableService {

    @Autowired
    private RoadMappMarkersJoinTableDao dao;

    public int[] getMarkersId(int roadmapp_id) {
        return new int[0];
    }

    public int getRoadmappId(int marker_id) {
        return 0;
    }

    public void saveAssociation(RoadMappMarkersJoinTable rmm) {
        dao.saveAssociation(rmm);
    }

    public void updateAssociation(RoadMappMarkersJoinTable rmm) {

    }

    public void deleteAssociation(int id) {
        dao.deleteAssociation(id);
    }
}
