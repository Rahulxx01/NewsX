package in.connectitude.newsx.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sources {
    @SerializedName("name")
    @Expose
    private String name;


    @SerializedName("id")
    @Expose
    private String id;


    public Sources() {

    }

    public Sources(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
