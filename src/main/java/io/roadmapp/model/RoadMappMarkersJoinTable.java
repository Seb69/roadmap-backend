package io.roadmapp.model;

/**
 * Created by guillaumenostrenoff on 06/01/2016.
 */

import javax.persistence.*;
import java.io.Serializable;

@NamedNativeQueries({
        @NamedNativeQuery(
                name="@GET_MARKERS_ID_BY_ROADMAPP_ID",
                query="select marker_id from ROADMAPP_MARKERS where roadmapp_id = :id "

        ),
        @NamedNativeQuery(
                name="@DELETE_ROADMAPP_MARKER_BY_MARKER_ID",
                query="delete from ROADMAPP_MARKERS where marker_id = :id "

        )
})



@Entity
@Table(name = "ROADMAPP_MARKERS")
public class RoadMappMarkersJoinTable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "marker_id")
    private long markerId;

    @Column(name = "roadmapp_id")
    private long roadmappId;

    public RoadMappMarkersJoinTable() {

    }

    public RoadMappMarkersJoinTable(long roadmappId, long markerId) {
        super();
        this.roadmappId = roadmappId;
        this.markerId = markerId;
    }
    /*public long getId() {
        return id;
    }*/

    public long getRoadmappId() {
        return roadmappId;
    }

    public void setRoadmappId(int roadmappId) {
        this.roadmappId = roadmappId;
    }

    public long getMarkerId() {
        return markerId;
    }

    public void setMarkerId(int markerId) {
        this.markerId = markerId;
    }
}
