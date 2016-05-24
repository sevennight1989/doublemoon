package lk.greedaotest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import de.greenrobot.dao.query.Query;
import lk.greedaotest.bean.Entity;
import lk.greedaotest.dao.DaoMaster;
import lk.greedaotest.dao.DaoSession;
import lk.greedaotest.dao.EntityDao;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "GreenDaoTest";
    SQLiteDatabase db;
    DaoSession daoSession;
    DaoMaster daoMaster;
    DaoMaster.DevOpenHelper helper;
    String orderBy;
    Cursor cursor;
    EntityDao entityDao;
    Button addBt;
    Button deleteBt;
    Button updateBt;
    Button queryBt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupDatabse();
        initViews();
        orderBy = EntityDao.Properties.Id.columnName + " DESC";
        cursor = db.query(entityDao.getTablename(), entityDao.getAllColumns(), null, null, null, null, orderBy);

    }

    private void setupDatabse() {
        helper = new DaoMaster.DevOpenHelper(this, "test", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        entityDao = daoSession.getEntityDao();
    }


    private void initViews() {
        addBt = (Button) findViewById(R.id.add);
        deleteBt = (Button) findViewById(R.id.delete);
        updateBt = (Button) findViewById(R.id.update);
        queryBt = (Button) findViewById(R.id.query);
        addBt.setOnClickListener(this);
        deleteBt.setOnClickListener(this);
        updateBt.setOnClickListener(this);
        queryBt.setOnClickListener(this);
    }

    private void addEntity() {
        Entity entity1 = new Entity(null, "zhansan", 23, 89D);
        Entity entity2 = new Entity(null, "lisi", 24, 92D);
        Entity entity3 = new Entity(null, "wangwu", 22, 81D);
        entityDao.insert(entity1);
        entityDao.insert(entity2);
        entityDao.insert(entity3);
        cursor.requery();
    }

    private void deleteEntity(long key) {
        entityDao.deleteByKey(key);
        cursor.requery();
    }

    private void updateEntity() {
        Entity entity = new Entity(2L, "lisi", 24, 95D);
        entityDao.update(entity);
    }

    private List<Entity> queryEntity(String name) {
        Query<Entity> query;
        if (TextUtils.isEmpty(name)) {
            query = entityDao.queryBuilder().orderAsc(EntityDao.Properties.Id).build();
        } else {
            query = entityDao.queryBuilder().where(EntityDao.Properties.Name.eq(name)).orderAsc(EntityDao.Properties.Id).build();
        }
        return query.list();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                addEntity();
                break;

            case R.id.delete:
                deleteEntity(1);
                break;

            case R.id.update:
                updateEntity();
                break;

            case R.id.query:
                List<Entity> list = queryEntity("lisi");

                for (Entity entity : list) {
                    Log.d(TAG, "id: " + entity.getId() + " name: " + entity.getName() + " age: " + entity.getName() + " score: " + entity.getScore());
                }
                break;

            default:
                break;

        }
    }
}
