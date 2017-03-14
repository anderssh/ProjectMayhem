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

        public ResultSet getAllWorkouts1() throws Exception {
            try {   
            		String queryString = "SELECT trening_ID, dato FROM trening";
                    statement = connect.createStatement();
                   // System.out.println("try-getAllWorkouts");
                    ResultSet workouts = null;
                    workouts = statement.executeQuery(queryString);
                    return workouts;
            } 
            catch (Exception e) {
                    throw e;
            }
        }
        
        public ResultSet getAllWorkouts() throws Exception {
            try {   
            	// Returns result from asking for the workout ID, date, sport and excercises for all workouts.
            		String queryString = "SELECT trening.trening_ID, trening.dato AS dato, idrett.navn AS idrett, ovelse.navn AS ovelse FROM trening ";
            		queryString = queryString + "JOIN trening_ovelse_detaljer ON trening.trening_ID=trening_ovelse_detaljer.trening_ID ";
            		queryString = queryString + "JOIN ovelse_detaljer ON trening_ovelse_detaljer.ovelse_detaljer_ID=ovelse_detaljer.ovelse_detaljer_ID ";
            		queryString = queryString + "JOIN ovelse ON ovelse_detaljer.ovelse_ID=ovelse.ovelse_ID ";
            		queryString = queryString + "JOIN idrett ON trening.idrett_ID=idrett.idrett_ID;";
            		
            		
                    statement = connect.createStatement();
                   // System.out.println("try-getAllWorkouts");
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
            		String queryString = "SELECT notat FROM trening WHERE trening_ID=" + trening_ID;
					statement = connect.createStatement();
					
					ResultSet workoutNotes = null;
					workoutNotes = statement.executeQuery(queryString);
					
					return workoutNotes;
            } 
            catch (Exception e) {
                    throw e;
            }
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

        public void addWorkout(ResultSet resultSet, int i, String a) throws SQLException {
        
        	
        }
        
        public ResultSet getAllExercises() throws Exception{
        	String queryString = "SELECT DISTINCT navn FROM ovelse";
        	statement = connect.createStatement();                
                ResultSet exercises = null;
                exercises = statement.executeQuery(queryString);
                return exercises;
        }

}