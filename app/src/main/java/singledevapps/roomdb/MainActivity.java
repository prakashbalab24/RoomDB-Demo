package singledevapps.roomdb;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import singledevapps.roomdb.dao.UserDao;
import singledevapps.roomdb.entity_model.User;

public class MainActivity extends AppCompatActivity {
    private EditText nameEt, phoneEt;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEt = findViewById(R.id.editText);
        phoneEt = findViewById(R.id.editText2);
        submitBtn = findViewById(R.id.button);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEt.getText().toString();
                String phone = phoneEt.getText().toString();
                User user = new User();
                user.setUser_name(name);
                user.setUser_phone(phone);
                new SaveData( UserRoomDB.getDatabase(MainActivity.this),user).execute();
            }
        });
    }
    private static class SaveData extends AsyncTask<Void, Void, Void>

    {

        private final UserRoomDB mDb;
        private User mUser;

        SaveData(UserRoomDB db, User user) {
            mDb = db;
            mUser = user;
            }

        @Override
        protected Void doInBackground(final Void... params) {
            mDb.userDao().insertAll(mUser);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.i("MainActivity","Executed....");
        }
    }
}
