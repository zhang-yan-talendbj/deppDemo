package com.caribe.stone.anki;

/**
 * Created by thinkdeeply on 11/5/14.
 */
public class IdObject {
    private long id;
    private long cid   ;

    public IdObject(long id, long cid) {
        this.id = id;
        this.cid = cid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }
}
