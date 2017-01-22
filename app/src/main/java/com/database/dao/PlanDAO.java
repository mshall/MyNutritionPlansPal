package com.database.dao;

/**
 * Created by KHoloud on 4/12/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.constants.Constants;
import com.database.helper.DatabaseHelper;
import com.pojo.Plan;

import java.util.ArrayList;

public class PlanDAO {

    Context context;
    DatabaseHelper databaseHelper;
    SQLiteDatabase database;

    public PlanDAO(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
    }

    //----------------------------------------------------------------
    //  Create new plan
    //----------------------------------------------------------------
    public void addPlan(int planNumber, String email) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.PLAN_NUMBER, planNumber);
        contentValues.put(Constants.EMAIL, email);
        database.insert(Constants.PLANS_TABLE, null, contentValues);
    }


    //----------------------------------------------------------------
    //  Get user plan given email
    //----------------------------------------------------------------
    public ArrayList<Plan> getUserPlans(String email) {
        ArrayList<Plan> plans = new ArrayList<>();
        Cursor c;
        String query = "Select *" +
                " from " + Constants.PLANS_TABLE +
                " where " + Constants.EMAIL + "='" + email + "'";

        c = database.rawQuery(query, null);
        while (c.moveToNext()) {
            Plan plan = new Plan();
            plan.setEmail(c.getString(c.getColumnIndex(Constants.EMAIL)));
            plan.setPlanNumber(c.getInt(c.getColumnIndex(Constants.PLAN_NUMBER)));
            plans.add(plan);
        }
        return plans;
    }

    //----------------------------------------------------------------
    //  Get last plan number
    //----------------------------------------------------------------
    public int getLastPlanNumber() {
        int planNumber = 0;
        Cursor c;
        String query = "Select MAX(" + Constants.PLAN_NUMBER + ")" +
                " from " + Constants.PLANS_TABLE + "";

        c = database.rawQuery(query, null);
        if (c != null) {
            if (c.moveToFirst()) {
                planNumber = c.getInt(c.getColumnIndex(Constants.PLAN_NUMBER));
            }
        } else {
            planNumber = 0;
        }
        return planNumber;
    }

    //----------------------------------------------------------------
    //  Delete all plans
    //----------------------------------------------------------------
    public boolean deleteAllPlans() {
        return database.delete(Constants.PLANS_TABLE, null, null) > 0;
    }
}
