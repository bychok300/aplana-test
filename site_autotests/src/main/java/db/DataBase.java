package db;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DataBase {

    private final String URL = "jdbc:mysql://bauman.doctoroncall.ru:3308/dev_doctoroncall";
    private final String USER = "site_dev";
    private final String PASSWORD = "blafBinBob3OineigtydOz6";

    private Connection connection = null;
    private Statement statement = null;

    public String getUser(String phone){

        String userId = null;
        ResultSet resultSet;

        try{
            resultSet = executeSqlQuery("select ID FROM b_user WHERE LOGIN = '7" + phone + "'");
            if(resultSet.next())
                userId = resultSet.getString(1);
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            closeConnections();
        }

        return userId;
    }

    public void setDefaultUserCode(String userId){
        try {
            executeSqlUpdate("UPDATE `doc_user_auth_code` SET `CODE` = 1111 WHERE `USER_ID` = " + userId + " AND `ACTIVE` = 1");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setOrderTestTherapist(String orderId){
        try {
            executeSqlUpdate("UPDATE `b_sale_order` SET `STATUS_ID` = 'B' WHERE `ID` = " + orderId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setDate(String orderId){
        Date nowDate = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            executeSqlUpdate("UPDATE `doc_calling_card` SET `DATE_CONFIRMATION` = " + "'" + formatForDateNow.format(nowDate) + "'" + ", `DATE_DESTINATION` = " + "'" + formatForDateNow.format(nowDate) + "'" + ", `DOCTOR_ID` = 22053 WHERE `ORDER_ID` = " + orderId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean createDmsToPatient(String patientId, String dmsNumber){
        try{
            executeSqlUpdate("INSERT INTO `doc_patient_to_dms` (`DATE_INSERT`, `DATE_UPDATE`, `PATIENT_ID`, `TRAFFIC_SOURCE_ID`, `COMPANY`, `DMS_NUMBER`, `ACTIVE`, `ACTIVE_FROM`, `ACTIVE_TO`, `COMMENT`)\n" +
                    "VALUES ('2017-04-01 18:14:08', '2017-06-22 17:08:33', '" + patientId + "', '43', 'BestDoctor', '" + dmsNumber + "', '1', '2017-01-13', '2018-05-13', 'text')");
            return true;
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }
    }

    private ResultSet executeSqlQuery(String query) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    private void executeSqlUpdate(String query) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        statement = connection.createStatement();
        statement.executeUpdate(query);
        closeConnections();
    }

    private void closeConnections(){
        try{
            connection.close();
            statement.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
