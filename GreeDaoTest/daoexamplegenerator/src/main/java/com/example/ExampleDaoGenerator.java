package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class ExampleDaoGenerator {

    public static void main(String[] args) throws Exception {
        int version = 1;
        String defaultPackage = "lk.greedaotest.bean";
        Schema schema = new Schema(version,defaultPackage);
        schema.setDefaultJavaPackageDao("lk.greedaotest.dao");
        addEntiry(schema);
        String outDir = "/home/uiprj/AndroidStudioProjects/GreeDaoTest/app/src/main/java-gen";
        new DaoGenerator().generateAll(schema,outDir);
    }

    private static void addEntiry(Schema schema){
        Entity entity = schema.addEntity("Entity");
        entity.setTableName("student");
        entity.addIdProperty().autoincrement();
        entity.addStringProperty("name").notNull();
        entity.addIntProperty("age");
        entity.addDoubleProperty("score");
    }

}
