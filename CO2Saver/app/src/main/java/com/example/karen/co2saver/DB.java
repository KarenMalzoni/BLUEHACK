package com.example.karen.co2saver;

import android.app.Application;
import android.database.Cursor;
import android.widget.Toast;
import java.sql.Connection;
import  java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Karen on 09/10/2018.
 */

public class DB extends Application {

    Connection connection;
    String url="";
    String dbName="BLUDB";
    String userName="ssz97898";
    String password="m5c6ad0";
    String serverName="dashdb-txn-sbox-yp-dal09-04.services.dal.bluemix.net";

    Statement statement;

    public void Open () {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
            url = "jdbc:db2://dashdb-txn-sbox-yp-dal09-04.services.dal.bluemix.net:50000/BLUDB:user=ssz97898;password=m5c6ad0";
            connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void Save (String sql) {
        try {
            statement = connection.createStatement();
            statement.execute(sql);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
