package com.softserve.edu.md.data;

import java.util.ArrayList;
import java.util.List;

public final class UserUtils {
    private String filename;
    private IExternalData externalData;

    public UserUtils() {
        filename = "/users.csv";
        externalData = new CSVUtils();
    }

    public UserUtils(String filename, IExternalData externalData) {
        this.filename = filename;
        this.externalData = externalData;
    }

    public List<IUser> getAllUsers() {
        return getAllUsers(this.getClass().getResource(filename).getPath().substring(1));
    }

    public List<IUser> getAllUsers(String absoluteFilePath) {
        List<IUser> users = new ArrayList<IUser>();
        for (List<String> row : externalData.getAllCells(absoluteFilePath)) {

            users.add(User.get()
                    .setFirstname(row.get(1))
                    .setLastname(row.get(2))
                    .setLogin(row.get(0))
                    .setPassword(row.get(3))
                    .setEmail(row.get(4))
                    .build());
        }
        return users;
    }

}