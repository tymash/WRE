package com.example.worldrealestate.async;

import android.os.AsyncTask;

import com.example.worldrealestate.db.RealEstateDao;
import com.example.worldrealestate.model.RealEstate;

public class AsyncDBUpdate extends AsyncTask<RealEstate, Void, Void> {

    private RealEstateDao dao;

    public AsyncDBUpdate(RealEstateDao dao) {
        this.dao = dao;
    }

    @Override
    protected Void doInBackground(RealEstate... realEstates) {
        dao.updateRealEstate(realEstates);
        return null;
    }
}
