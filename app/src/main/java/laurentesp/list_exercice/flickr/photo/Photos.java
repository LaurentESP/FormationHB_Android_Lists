package laurentesp.list_exercice.flickr.photo;

import java.util.ArrayList;

/**
 * Created by SOEOSSA on 17/10/2016.
 */

public class Photos
{
    private String total;

    private String page;

    private String pages;

    private ArrayList<Photo> photo;

    private String perpage;

    public String getTotal ()
    {
        return total;
    }

    public void setTotal (String total)
    {
        this.total = total;
    }

    public String getPage ()
    {
        return page;
    }

    public void setPage (String page)
    {
        this.page = page;
    }

    public String getPages ()
    {
        return pages;
    }

    public void setPages (String pages)
    {
        this.pages = pages;
    }

    public ArrayList<Photo> getPhoto ()
    {
        return photo;
    }

    public void setPhoto (ArrayList<Photo> photo)
    {
        this.photo = photo;
    }

    public String getPerpage ()
    {
        return perpage;
    }

    public void setPerpage (String perpage)
    {
        this.perpage = perpage;
    }

    @Override
    public String toString()
    {
        return "ClassPhotos [total = "+total+", page = "+page+", pages = "+pages+", photo = "+photo+", perpage = "+perpage+"]";
    }
}
