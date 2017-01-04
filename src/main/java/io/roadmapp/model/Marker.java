package io.roadmapp.model;

/**
 * Created by guillaumenostrenoff on 06/01/2016.
 */

import java.io.Serializable;

import javax.persistence.*;

@NamedNativeQueries({
        @NamedNativeQuery(
                name="@GET_MARKER_BY_ID_BY_INDEX",
                query="select * from MARKER where roadmapp_id = :id and marker_index = :index",
                resultClass = Marker.class
        ),
        @NamedNativeQuery(
        name="@DELETE_MARKER_BY_ID",
        query="delete from MARKER where marker_id = :id "
        )
})

@Entity
@Table(name = "MARKER")
public class Marker implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "marker_id", unique = true, nullable = false)
    private int markerId;

    @Column(name = "roadmapp_id")
    private int roadmappId;

    @Column(name = "LAT")
    private double lat;

    @Column(name = "LNG")
    private double lng;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "MARKER_INDEX")
    private int mIndex;

    /************************** working on this .. *********************

     @ManyToOne
     @JoinColumn(name = "roadmapp_id")
     private RoadMapp roadmapp;

     /*******************************************************************/


    public Marker() {

    }

    public Marker(int lat, int lng, String title) {
        super();
        this.lat = lat;
        this.lng = lng;
        this.title = title;

    }

    public int getmIndex() {
        return mIndex;
    }

    public void setmIndex(int mIndex) {
        this.mIndex = mIndex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRoadmappId() {
        return roadmappId;
    }

    public void setRoadmappId(int roadmappId) {
        this.roadmappId = roadmappId;
    }

    public int getId() {
        return markerId;
    }

    public void setId(int id) {
        this.markerId = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

}
