package vn.quanghuy.cinemaschedule.activity.bean;

import android.graphics.Bitmap;

/**
 * Created by huy on 29/11/2015.
 */
public abstract class BaseObject {

    private short id;
    private Bitmap icon;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }
}
