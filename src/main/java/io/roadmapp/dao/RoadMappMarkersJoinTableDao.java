package io.roadmapp.dao;

import io.roadmapp.model.RoadMappMarkersJoinTable;

public interface RoadMappMarkersJoinTableDao {

    int[] getMarkersId(int roadmapp_id);

    int getRoadmappId(int marker_id);

    void saveAssociation(RoadMappMarkersJoinTable rmm);

    void updateAssociation(RoadMappMarkersJoinTable rmm);

    void deleteAssociation(int id);

}