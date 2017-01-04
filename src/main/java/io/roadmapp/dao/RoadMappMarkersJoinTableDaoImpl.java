package io.roadmapp.dao;

import io.roadmapp.model.RoadMappMarkersJoinTable;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository("roadMappMarkersDao")
public class RoadMappMarkersJoinTableDaoImpl extends AbstractDao<Integer, RoadMappMarkersJoinTable> implements RoadMappMarkersJoinTableDao {


    public int[] getMarkersId(int roadmapp_id) {
        return new int[0];
    }

    public int getRoadmappId(int marker_id) {
        return 0;
    }

    public void saveAssociation(RoadMappMarkersJoinTable rmm) {
        persist(rmm);
    }

    public void updateAssociation(RoadMappMarkersJoinTable rmm) {
        //TODO
    }

    public void deleteAssociation(int id) {

        Query query = getSession().createSQLQuery("delete from ROADMAPP_MARKERS where marker_id = :id");
        query.setInteger("id", id);
        query.executeUpdate();

    }
}