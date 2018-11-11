package in.connectitude.newsx.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {


    @SerializedName("id")
    @Expose
    private Object id;

    @SerializedName("name")
    @Expose
    private String name;


    public Source() {

    }

    public Source(Object id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Object getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
