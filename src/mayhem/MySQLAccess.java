package mayhem;

import java.sql.*;
import java.util.Date;
import java.util.Properties;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import mayhem.PropertyHandling;

@SuppressWarnings("unused")
public class MySQLAccess {
        private Connection connect = null;
        private PreparedStatement insert_idrett = null;
        private PreparedStatement list_idrett = null;
        private ResultSet resultSet = null;
        private Properties props = null;
<<<<<<< HEAD
		
        
        
        
        
        public void readDataBase() throws Exception {
                try {
                		//Load properties
                		PropertyHandling propHandling = new PropertyHandling();
                		props = propHandling.LoadDatabaseProperies();
                	
                        // This will load the MySQL driver, each DB has its own driver
                        Class.forName(props.getProperty("dbdriver"));
                        
                        // Setup the connection with the DB
                        MysqlDataSource dataSource = new MysqlDataSource();
                        
                        dataSource.setUser(props.getProperty("dbuser"));
                        dataSource.setPassword(props.getProperty("dbpassword"));
                        dataSource.setURL(props.getProperty("dbURL"));
                        
                        connect = dataSource.getConnection();
                        
                        // Statements allow to issue SQL queries to the database
                        statement = connect.createStatement();
                        // Result set get the result of the SQL query
                        resultSet = statement.executeQuery("select * from idrett");
                        writeResultSet(resultSet);

                        // PreparedStatements can use variables and are more efficient 
                        insert_idrett = connect.prepareStatement("INSERT INTO  idrett (navn) VALUES (?)");
                        // IDen auto-oppdaterer seg.
                        
                        System.out.println("Nå er det lagt inn noe mer her, så nå blir det mer neste gang");
                        // Parameters start with 1
                        insert_idrett.setString(1, "Tennis");
                        insert_idrett.executeUpdate();
                        insert_idrett.setString(1, "Tennis");
                        
                        list_idrett = connect.prepareStatement("SELECT * FROM idrett");
                        resultSet = list_idrett.executeQuery();
                       
                        writeResultSet(resultSet);
                        writeMetaData(resultSet);

                } catch (Exception e) {
                        throw e;
                } finally {
                        close();
                }

=======
        private Statement statement = null;
        
        public void makeConnection() throws Exception {
        	
        	//Load properties
    		PropertyHandling propHandling = new PropertyHandling();
    		props = propHandling.LoadDatabaseProperies();
    	
            // This will load the MySQL driver, each DB has its own driver
            Class.forName(props.getProperty("dbdriver"));
            
            // Setup the connection with the DB
            MysqlDataSource dataSource = new MysqlDataSource();
            
            dataSource.setUser(props.getProperty("dbuser"));
            dataSource.setPassword(props.getProperty("dbpassword"));
            dataSource.setURL(props.getProperty("dbURL"));
            
            connect = dataSource.getConnection();	
        }
			
//        public void readDataBase() throws Exception {
//                try {   
//                        // PreparedStatements can use variables and are more efficient 
//                        insert_idrett = connect.prepareStatement("INSERT INTO  idrett (navn) VALUES (?)");
//                        // IDen auto-oppdaterer seg.
//                        
//                        insert_idrett.setString(1, "Tennis");
//                        insert_idrett.executeUpdate();
//                       
//                        writeResultSet(resultSet);
//                        writeMetaData(resultSet);
//
//                } catch (Exception e) {
//                        throw e;
//                }
//        }
        
        public ResultSet getAllWorkouts() throws Exception {
            try {   
            		String queryString = "SELECT trening_ID, dato FROM trening_ID";
                    statement = connect.createStatement();
                    ResultSet workouts = null;
                    workouts = statement.executeQuery(queryString);
                    return workouts;
            } 
            catch (Exception e) {
                    throw e;
            }
        }
        
        public ResultSet getWorkoutOnID(int trening_ID) throws Exception {
        	try {
            		String queryString = "SELECT  * FROM trening WHERE trening_ID="+trening_ID;
					statement = connect.createStatement();
					
					ResultSet workout = null;
					workout = statement.executeQuery(queryString);
					
					return workout;
            } 
            catch (Exception e) {
                    throw e;
            }
        }
        
        public ResultSet getWorkoutsWithNotes() throws Exception {
        	try {
            		String queryString = "SELECT trening_ID, dato FROM trening WHERE notat IS NOT NULL";
					statement = connect.createStatement();
					
					ResultSet workoutsWithNotes = null;
					workoutsWithNotes = statement.executeQuery(queryString);
					
					return workoutsWithNotes;
            } 
            catch (Exception e) {
                    throw e;
            }
        }
        
        public ResultSet getNotesOnWorkoutID(int trening_ID) throws Exception {
        	try {
            		String queryString = "SELECT notat, FROM trening WHERE trening_ID="+trening_ID;
					statement = connect.createStatement();
					
					ResultSet workoutNotes = null;
					workoutNotes = statement.executeQuery(queryString);
					
					return workoutNotes;
            } 
            catch (Exception e) {
                    throw e;
            }
>>>>>>> 9777a5781eeb0ad4ab7adf1a3139ef358c21aa7c
        }

        private void writeMetaData(ResultSet resultSet) throws SQLException {
                //         Now get some metadata from the database
                // Result set get the result of the SQL query

                System.out.println("The columns in the table are: ");

                System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
                for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
                        System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
                }
        }

        private void writeResultSet(ResultSet resultSet) throws SQLException {
                // ResultSet is initially before the first data set
        		int allId = 0;
                while (resultSet.next()) {
                        // It is possible to get the columns via name
                        // also possible to get the columns via the column number
                        // which starts at 1
                        // e.g. resultSet.getString(2);
                        int idrett_id = resultSet.getInt("idrett_id");
                        String navn = resultSet.getString("navn");
                        System.out.println("ID: " + idrett_id + "      Navn:" + navn);
                }
        }

//         You need to close the resultSet
        private void close() {
                try {
                        if (resultSet != null) {
                                resultSet.close();
                        }

                        if (statement != null) {
                                statement.close();
                        }

                        if (connect != null) {
                                connect.close();
                        }
                } catch (Exception e) {

                }
        }

}