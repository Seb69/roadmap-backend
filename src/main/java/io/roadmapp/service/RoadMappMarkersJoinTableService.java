package io.roadmapp.service;

import io.roadmapp.model.RoadMappMarkersJoinTable;

public interface RoadMappMarkersJoinTableService {

    int[] getMarkersId(int roadmapp_id);

    int getRoadmappId(int marker_id);

    void saveAssociation(RoadMappMarkersJoinTable rmm);

    void updateAssociation(RoadMappMarkersJoinTable rmm);

    void deleteAssociation(int id);

}
