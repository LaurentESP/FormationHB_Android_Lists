package laurentesp.list_exercice;

/**
 * Created by SOEOSSA on 17/10/2016.
 */

public class PhotoSimple {
    private String url;
    private String title;

    public PhotoSimple(String title, String farm, String server, String id, String secret) {
        this.title = title;
        setUrl(farm, server, id, secret);
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrl(String farm, String server, String id, String secret) {
        url = "https://farm"+farm+".static.flickr.com/"+server+"/"+id+"_"+secret+".jpg";
    }

}
