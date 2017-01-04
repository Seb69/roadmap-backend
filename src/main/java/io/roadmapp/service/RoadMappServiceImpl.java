package io.roadmapp.service;

import io.roadmapp.dao.RoadMappDao;
import io.roadmapp.model.RoadMapp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roadmappService")
@Transactional
public class RoadMappServiceImpl implements RoadMappService {

    @Autowired
    private RoadMappDao dao;

    public RoadMapp getRoadMapp(int roadmapp_id) {
        return dao.getRoadMapp(roadmapp_id);
    }

    public void saveRoadMapp(RoadMapp rm) {
        dao.saveRoadMapp(rm);
    }

    public void updateRoadMapp(RoadMapp rm) {

        RoadMapp entity = dao.getRoadMapp(rm.getId());
        if(entity!=null){
            entity.setTitle(rm.getTitle());
            entity.setDescription(rm.getDescription());
        }

    }

    public void deleteRoadMapp(int roadmapp_id) {
        dao.deleteRoadMapp(roadmapp_id);
    }

    public List<RoadMapp> getRoadMapps() {
        return dao.getRoadMapps();
    }
}
