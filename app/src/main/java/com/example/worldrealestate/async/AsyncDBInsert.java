package com.example.worldrealestate.async;

import android.os.AsyncTask;

import com.example.worldrealestate.db.RealEstateDao;
import com.example.worldrealestate.model.RealEstate;

public class AsyncDBInsert extends AsyncTask<RealEstate, Void, Void> {

    private RealEstateDao dao;

    public AsyncDBInsert(RealEstateDao dao) {
        this.dao = dao;
    }

    @Override
    protected Void doInBackground(RealEstate... realEstates) {
        dao.insertRealEstate(realEstates);
        return null;
    }
}
