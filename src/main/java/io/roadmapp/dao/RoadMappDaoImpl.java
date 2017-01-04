package io.roadmapp.dao;

import com.google.gson.Gson;
import io.roadmapp.model.CurrentUserDetails;
import io.roadmapp.model.RoadMapp;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CurrencyEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Repository("roadMappDao")
public class RoadMappDaoImpl extends AbstractDao<Integer, RoadMapp> implements RoadMappDao {

    public RoadMapp getRoadMapp(int roadmapp_id) {
        //Object to return
        RoadMapp roadmapp = new RoadMapp();

        Gson gson = new Gson();

        //Search on DATABASE : GET_MARKERS_ID_BY_ROADMAPP_ID
        //Param : roadmapp_id
        Query query =  getSession().getNamedQuery("@GET_MARKERS_ID_BY_ROADMAPP_ID");
        query.setInteger("id",roadmapp_id);

        //Add markersIds within object to return
        roadmapp.setMarkersIds(query.list());

        //Search on DATABASE : GET_ROADMAPP_BY_ID
        //Param : roadmapp_id filtering by user_id
        query =  getSession().getNamedQuery("@GET_ROADMAPP_BY_ID");
        query.setInteger("id", roadmapp_id);

        //Retrieve result list
        List<RoadMapp> roadmappList = new ArrayList<RoadMapp>();
        roadmappList=query.list();
        roadmapp.setTitle(roadmappList.get(0).getTitle());
        roadmapp.setDescription(roadmappList.get(0).getDescription());
        roadmapp.setRoadmappId(roadmappList.get(0).getId());
        roadmapp.setUserId(roadmappList.get(0).getUserId());

        return roadmapp;
    }

    public void saveRoadMapp(RoadMapp rm) {
        persist(rm);
    }

    public void deleteRoadMapp(int roadmapp_id) {

        //CREATE QUERY : GET ALL MARKERS IDS
        Query query =  getSession().getNamedQuery("@GET_MARKERS_ID_BY_ROADMAPP_ID");
        query.setInteger("id",roadmapp_id);

        //GET RESULT QUERY
        List<Integer> listMarkers = new ArrayList<Integer>();
        listMarkers.addAll(query.list());


            for (Integer id : listMarkers) {

                //Delete Table MARKER
                query = getSession().getNamedQuery("@DELETE_MARKER_BY_ID");
                query.setInteger("id", id);
                query.executeUpdate();

                //Delete Table ROADMAPP_MARKER
                query = getSession().getNamedQuery("@DELETE_ROADMAPP_MARKER_BY_MARKER_ID");
                query.setInteger("id", id);
                query.executeUpdate();

            }

        //Delete Table ROADMAPP
        query = getSession().getNamedQuery("@DELETE_ROADMAPP_BY_ID");
        query.setInteger("roadmapp_id", roadmapp_id);
        query.executeUpdate();



    }

    public ArrayList<RoadMapp> getRoadMapps() {
        CurrentUserDetails currentUser = (CurrentUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("userId", currentUser.getId()));
        return (ArrayList<RoadMapp>) criteria.list();
    }
}