package io.roadmapp.dao;

import com.google.gson.Gson;
import io.roadmapp.model.Marker;
import io.roadmapp.model.RoadMapp;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("markerDao")
public class MarkerDaoImpl extends AbstractDao<Integer, Marker> implements MarkerDao {

    public Marker getMarker(int marker_id) {
        return getByKey(marker_id);
    }

    public Marker getMarker(int marker_id, int index) {


        System.out.println(marker_id +" : "+ index);

        //Object to return
        Marker marker = new Marker();

        Gson gson = new Gson();

        //Search on DATABASE : GET_MARKER_BY_ID_BY_INDEX
        //Param : marker_id, index
        Query query =  getSession().getNamedQuery("@GET_MARKER_BY_ID_BY_INDEX");
        query.setInteger("id",marker_id);
        query.setInteger("index",index);


        //Retrieve result list
        List<Marker> markerList = new ArrayList<Marker>();
        markerList=query.list();
        marker.setId(markerList.get(0).getId());
        marker.setTitle(markerList.get(0).getTitle());
        marker.setmIndex(markerList.get(0).getmIndex());
        marker.setLat(markerList.get(0).getLat());
        marker.setLng(markerList.get(0).getLng());
        return marker;
    }

    public void saveMarker(Marker marker) { persist(marker); }

    public void deleteMarker(int marker_id) {

        Query query = getSession().createSQLQuery("delete from Marker where marker_id = :marker_id");
        query.setInteger("marker_id", marker_id);
        query.executeUpdate();
    }

    public ArrayList<Marker> getMarkers() {
        Criteria criteria = createEntityCriteria();
        return (ArrayList<Marker>) criteria.list();
    }
}