/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public abstract class AbstractDomainObject implements Serializable {
    public abstract String getTableName();
    public abstract String getColumnsForInsert();
    public abstract String getParamsForInsert();
    public abstract String getParamsForUpdate();
    public abstract ArrayList<AbstractDomainObject> getList(ResultSet resultSet) throws SQLException;
    public abstract String alias();
    public abstract String join();
    public abstract String condition();
    public abstract String valueForPK();
}
