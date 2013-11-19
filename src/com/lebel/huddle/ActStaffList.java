package com.lebel.huddle;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.ianywhere.ultralitejni12.ConfigPersistent;
import com.ianywhere.ultralitejni12.Connection;
import com.ianywhere.ultralitejni12.DatabaseManager;
import com.ianywhere.ultralitejni12.PreparedStatement;
import com.ianywhere.ultralitejni12.ResultSet;
import com.ianywhere.ultralitejni12.ULjException;
import com.lebel.huddle.R;
//import com.lebel.huddle.UltraDatabaseHelper;

import android.os.Bundle;
import android.app.Activity;
//import android.database.SQLException;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActStaffList extends Activity {
	
	private ArrayList<String> StaffList;
	private ConfigPersistent _config;
	private static Connection _dbconn;
//  private static final int defaultPort = 2639;
	private static final String defaultUsername = "dba";
	private static final String defaultPassword = "sql";
	private static final String db = "lebeldb.udb";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_staff_list);
		
		try {
			setupUiEvents();
		} catch (ULjException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setupUiEvents() throws ULjException, IOException {
		copyDatabaseToDevice();
		_config = DatabaseManager.createConfigurationFileAndroid(db, this.getApplicationContext());
		_config.setUserName(defaultUsername);
        _config.setPassword(defaultPassword);
        _config.setPageSize(8192);

	    _dbconn = DatabaseManager.connect(_config);
		String qry = "SELECT FirstName FROM tblAvailableStaff";
        PreparedStatement ps = _dbconn.prepareStatement(qry);
        ResultSet rs = ps.executeQuery();
        StaffList = new ArrayList<String>();
        while (rs.next()) {
            StaffList.add(rs.getString(1));
        }
        rs.close();
        ps.close();
        ListView lv = (ListView) findViewById(R.id.lvStaffList);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, StaffList));
	}

	private void copyDatabaseToDevice() throws IOException {
		String dbTargetPath = this.getApplicationInfo().dataDir;
		dbTargetPath += dbTargetPath + "/" + db;
		InputStream myInput = this.getApplicationContext().getAssets().open(db);
	    //String outFileName = dbTargetPath + "/" + db;
		String outFileName = dbTargetPath;
	    OutputStream myOutput = new FileOutputStream(outFileName);
	    byte[] buffer = new byte[1024];
	    int length;
	    while ((length = myInput.read(buffer))>0) {
	    	myOutput.write(buffer, 0, length);
	    }
	      myOutput.flush();
	      myOutput.close();
	      myInput.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act_staff_list, menu);
		return true;
	}

}