package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class appRepository {
    private static final Logger logger = LoggerFactory.getLogger(appRepository.class);



    public List<appBasicView> tableMenu() {
        try (Connection connection = databaseConn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT id_member, first_name,last_name,birthday,weight,city,membership_name FROM bds.member x LEFT JOIN bds.address c ON c.id_address =x.address LEFT JOIN bds.membership_card d ON d.id_membership_card=x.membership_card_id LEFT JOIN bds.type_of_membership e ON e.id_type_of_membership=d.id_membership_card;");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<appBasicView> personBasicViews = new ArrayList<>();
            while (resultSet.next()) {
                personBasicViews.add(mapToAppView(resultSet));
            }
            return personBasicViews;
        } catch (SQLException e) {
            throw new DataAccessException("Persons basic view could not be loaded.", e);
        }
    }
    public MemberDetailView findPersonDetailedView(Long id_member) {
        try (Connection connection = databaseConn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT id_member,email,first_name,last_name,telephone,city,number,street FROM bds.member x LEFT JOIN bds.address a ON x.address=a.id_address LEFT JOIN bds.contact b ON x.id_contact=b.id_contact WHERE id_member = ?")) {
            preparedStatement.setLong(1, id_member);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToPersonDetailView(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Find person by ID with addresses failed.", e);

        }
        return null;
    }
    public List<appBasicView> getFilterView(Long id){

        try (Connection connection = databaseConn.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT id_member, first_name,last_name,birthday,weight,city,membership_name FROM bds.member x LEFT JOIN bds.address c ON c.id_address =x.address LEFT JOIN bds.membership_card d ON d.id_membership_card=x.membership_card_id LEFT JOIN bds.type_of_membership e ON e.id_type_of_membership=d.id_membership_card where id_member=? "
             )



        ) {
            preparedStatement.setLong(1, id);
            return mapToAppView(preparedStatement,connection);
        }
        catch (SQLException e) {
            throw new DataAccessException("filter view could not be loaded.", e);
        }
    }
    public void createPerson(memberCreateView personCreateView) {
        String insertPersonSQL = "INSERT INTO bds.member (first_name, last_name, birthday,weight) VALUES (?,?,?,?) ";
        try (Connection connection = databaseConn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, personCreateView.getName());
            preparedStatement.setString(2, personCreateView.getSurname());
            preparedStatement.setDate(3, personCreateView.getBirthday());
            preparedStatement.setInt(4, personCreateView.getWeight());


            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new DataAccessException("Creating person failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Creating person failed operation on the database failed.");
        }
    }

    public void remove(Long id) {
        String deleteSQL =  "DELETE FROM bds.member WHERE id_member = ?";
        System.out.println(deleteSQL);
        try (Connection connection = databaseConn.getConnection();
             PreparedStatement p = connection.prepareStatement(deleteSQL)){

            p.setLong(1, id);
            p.executeUpdate();

        }
        catch (SQLException e) {
            logger.info("failed");
        }

    }
    public void editPerson(appEditView EditView) {
        String insertPersonSQL = "UPDATE bds.member SET first_name=?, last_name=?, birthday=?, weight=? WHERE id_member=?";



        String checkIfExists = "SELECT id_member FROM bds.member WHERE id_member=? ";

        try (Connection connection = databaseConn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, EditView.getName());
            preparedStatement.setString(2, EditView.getSurname());
            preparedStatement.setDate(3, EditView.getBirthday());
            preparedStatement.setInt(4, EditView.getWeight());
            preparedStatement.setLong(5, EditView.getId());
            System.out.println(preparedStatement);
            try {

                connection.setAutoCommit(false);
                try (PreparedStatement ps = connection.prepareStatement(checkIfExists, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setLong(1, EditView.getId());

                    ps.execute();
                } catch (SQLException e) {
                    throw new DataAccessException("Not existing member to edit");
                }

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new DataAccessException("Creating book failed, no rows affected.");
                }

                System.out.println(connection);
                connection.commit();
            } catch (SQLException e) {

                connection.rollback();
            } finally {

                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Creating book failed operation on the database failed.");
        }
    }





    private static List<appBasicView> mapToAppView(PreparedStatement prpstmt, Connection connection) throws SQLException {
        List<appBasicView> view = new ArrayList<>();
        ResultSet rs = prpstmt.executeQuery();
        while (rs.next())
        {
            appBasicView basic = new appBasicView();
            basic.setId(rs.getLong("id_member"));
            basic.setName(rs.getString("first_name"));
            basic.setSurname(rs.getString("last_name"));
            basic.setBirthday(rs.getString("birthday"));
            basic.setWeight(rs.getInt("weight"));
            basic.setMembership(rs.getString("membership_name"));
            basic.setAddress(rs.getString("city"));
            view.add(basic);
        }
        return view;
    }

    private static appBasicView mapToAppView(ResultSet rs) throws SQLException {
        appBasicView basic = new appBasicView();
        basic.setId(rs.getLong("id_member"));
        basic.setName(rs.getString("first_name"));
        basic.setSurname(rs.getString("last_name"));
        basic.setBirthday(rs.getString("birthday"));
        basic.setWeight(rs.getInt("weight"));
        basic.setAddress(rs.getString("city"));
        basic.setMembership(rs.getString("membership_name"));
        return basic;
    }
    private MemberDetailView mapToPersonDetailView(ResultSet rs) throws SQLException {
        MemberDetailView memberDetailView = new MemberDetailView();
        memberDetailView.setId(rs.getLong("id_member"));
        memberDetailView.setEmail(rs.getString("email"));
        memberDetailView.setName(rs.getString("first_name"));
        memberDetailView.setSurname(rs.getString("last_name"));
        memberDetailView.setTelephone(rs.getString("telephone"));
        memberDetailView.setCity(rs.getString("city"));
        memberDetailView.setStreetNumber(rs.getString("number"));
        memberDetailView.setStreet(rs.getString("street"));
        return memberDetailView;
    }
    public List<InjectionView> getInjectionView(String input)
    {
        String injection = "SELECT person_id, first_name,last_name, nickname, email FROM bds.dummy WHERE person_id =" + input;
        try (Connection conn = databaseConn.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(injection))
        {
            List<InjectionView> injectionViews = new ArrayList<>();
            while (rs.next())
            {
                injectionViews.add(mapToInjectionView(rs));
            }
            return injectionViews;
        } catch (SQLException e)
        {
            logger.error("Failed to find users\nMessage:" + e.getMessage());
        }
        return null;
    }

    private InjectionView mapToInjectionView(ResultSet rs) throws SQLException
    {
        InjectionView injectionView = new InjectionView();
        injectionView.setId(rs.getLong("person_id"));
        injectionView.setFirstName(rs.getString("first_name"));
        injectionView.setLastName(rs.getString("last_name"));
        injectionView.setNickName(rs.getString("nickname"));
        injectionView.setEmail(rs.getString("email"));
        return injectionView;
    }
}




