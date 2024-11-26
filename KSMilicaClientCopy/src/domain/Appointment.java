/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Appointment extends AbstractDomainObject {

    private Long appointmentId;
    private LocalDate date;
    private Client client;
    private Service service;

    public Appointment() {
    }

    public Appointment(Long appointmentId, LocalDate date, Client client, Service service) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.client = client;
        this.service = service;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String getTableName() {
        return "appointment";
    }

    @Override
    public String getColumnsForInsert() {
        return "(date, clientId, serviceId)";
    }

    @Override
    public String getParamsForInsert() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);

        return "'" + formattedDate + "', '" + client.getClientId() + "', '"
                + service.getServiceId() + "'";

    }

    @Override
    public String getParamsForUpdate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);

        return "date = '" + formattedDate + ", clientId = '" + client.getClientId() + "', serviceId = '" + service.getServiceId() + "'";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet resultSet) throws SQLException {
        ArrayList<AbstractDomainObject> list = new ArrayList<>();

        while (resultSet.next()) {

            Service s = new Service(
                    resultSet.getLong("serviceId"),
                    resultSet.getString("name"),
                    resultSet.getDouble("price")
            );

            Client c = new Client(
                    resultSet.getLong("clientId"),
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("email"),
                    null
            );

            LocalDate date = resultSet.getDate("date").toLocalDate();

            Appointment a = new Appointment(
                    resultSet.getLong("appointmentId"),
                    date,
                    c,
                    s
            );

            list.add(a);
        }

        resultSet.close();
        return list;
    }

    @Override
    public String alias() {
        return "a";
    }

    @Override
    public String join() {
        return "JOIN CLIENT C ON (C.CLIENTID = A.CLIENTID) "
                + "JOIN SERVICE S ON (S.SERVICEID = A.SERVICEID) ";
    }

    @Override
    public String condition() {
        return "ORDER BY A.DATE DESC";
    }

    @Override
    public String valueForPK() {
        return "appointmentId = " + appointmentId;
    }

}
