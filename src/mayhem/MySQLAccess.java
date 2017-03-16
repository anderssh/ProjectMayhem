package mayhem;

import java.sql.*;
import java.util.Date;
import java.util.Properties;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import mayhem.PropertyHandling;

@SuppressWarnings("unused")
public class MySQLAccess {
        private Connection connect = null;
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
        
<<<<<<< HEAD

        public ResultSet getInsideWorkoutOnID(int trening_ID) throws Exception {
        	try {
        		String queryString = "SELECT trening.trening_ID, idrett.navn AS idrett, 'Innetrening' AS innetrening, ovelse.navn AS ovelse, ";
        		queryString = "belastning_kg, antall_set, antall_repetisjoner, ovelse_detaljer.varighet AS ovelse_varighet, ventilasjon, antall_tilskuere  FROM trening ";
        		queryString = "JOIN trening_ovelse_detaljer ON trening.trening_ID=trening_ovelse_detaljer.trening_ID ";
        		queryString = "JOIN ovelse_detaljer ON trening_ovelse_detaljer.ovelse_detaljer_ID=ovelse_detaljer.ovelse_detaljer_ID ";
        		queryString = "JOIN ovelse ON ovelse_detaljer.ovelse_ID=ovelse.ovelse_ID ";
        		queryString = "JOIN idrett ON trening.idrett_ID=idrett.idrett_ID ";
        		queryString = "JOIN innetrening ON trening.trening_ID=innetrening.trening_ID";
        		queryString = queryString + " WHERE innetrening.trening_ID= ? ;";
        		PreparedStatement prepStat = null;
=======
        public ResultSet getInsideWorkoutOnID(int trening_ID) throws Exception {
        	try {
                String queryString = "SELECT trening.trening_ID, idrett.navn AS idrett, 'Innetrening' AS innetrening, ovelse.navn AS ovelse, ";
                queryString = queryString + "belastning_kg, antall_set, antall_repetisjoner, ovelse_detaljer.varighet AS ovelse_varighet, ventilasjon, antall_tilskuere  FROM trening ";
                queryString = queryString + "JOIN trening_ovelse_detaljer ON trening.trening_ID=trening_ovelse_detaljer.trening_ID ";
                queryString = queryString + "JOIN ovelse_detaljer ON trening_ovelse_detaljer.ovelse_detaljer_ID=ovelse_detaljer.ovelse_detaljer_ID " ;
                queryString = queryString + "JOIN ovelse ON ovelse_detaljer.ovelse_ID=ovelse.ovelse_ID " ;
                queryString = queryString + "JOIN idrett ON trening.idrett_ID=idrett.idrett_ID " ;
                queryString = queryString + "JOIN innetrening ON trening.trening_ID=innetrening.trening_ID " ;
                queryString = queryString + " WHERE innetrening.trening_ID= ? ;";
                PreparedStatement prepStat = null;
>>>>>>> 0146a51c760bc69e156a5080a877b1e1c5fc9b42
				
        		prepStat = connect.prepareStatement(queryString);
				prepStat.setInt( 1, trening_ID);
        		
				ResultSet innetrening = null;
				innetrening = prepStat.executeQuery();
				return innetrening;
        } 
        catch (Exception e) {
                throw e;
        }
        }
        
        public ResultSet getOutsideWorkoutOnID(int trening_ID) throws Exception {
        	try {
                String queryString = "SELECT trening.trening_ID, idrett.navn AS idrett, 'utetrening' AS inneUte, ovelse.navn AS ovelse, ";
                queryString = queryString +  "belastning_kg, antall_set, antall_repetisjoner, ovelse_detaljer.varighet AS ovelse_varighet, vaertype, temperatur  FROM trening ";
                queryString = queryString + "JOIN trening_ovelse_detaljer ON trening.trening_ID=trening_ovelse_detaljer.trening_ID ";
                queryString = queryString + "JOIN ovelse_detaljer ON trening_ovelse_detaljer.ovelse_detaljer_ID=ovelse_detaljer.ovelse_detaljer_ID ";
                queryString = queryString + "JOIN ovelse ON ovelse_detaljer.ovelse_ID=ovelse.ovelse_ID ";
                queryString = queryString + "JOIN idrett ON trening.idrett_ID=idrett.idrett_ID ";
                queryString = queryString + "JOIN utetrening ON trening.trening_ID=utetrening.trening_ID ";
                queryString = queryString + " WHERE utetrening.trening_ID= ? ;";
                PreparedStatement prepStat = null;
        			
        		prepStat = connect.prepareStatement(queryString);
        			prepStat.setInt( 1, trening_ID);
        		
        			ResultSet utetrening = null;
        			utetrening = prepStat.executeQuery();
        			return utetrening;
        } 
        catch (Exception e) {
                throw e;
        }
        }
        
        public ResultSet getWorkoutOnID(int trening_ID) throws Exception {
        	try {
            		String queryString = "SELECT trening.trening_ID FROM trening JOIN innetrening ON trening.trening_ID=innetrening.trening_ID";
            		queryString = queryString + " WHERE innetrening.trening_ID= ? ;";
            		PreparedStatement prepStat = null;
            		
					prepStat = connect.prepareStatement(queryString);
					prepStat.setInt( 1, trening_ID);

					ResultSet innet = null;
					innet = prepStat.executeQuery();
					
            		queryString = "SELECT trening.trening_ID FROM trening JOIN utetrening ON trening.trening_ID=utetrening.trening_ID";
            		queryString = queryString + " WHERE utetrening.trening_ID= ? ;";
            		
					prepStat = connect.prepareStatement(queryString);
					prepStat.setInt( 1, trening_ID);

					ResultSet utet = null;
					utet = prepStat.executeQuery();
					
					if (innet.next() == false) {
						System.out.println("Innetrening er false");
						
						if (utet.next() == false) {
							System.out.println("Begge er false");
						}
						else { 
							ResultSet outsideWorkout = null;
							outsideWorkout = getOutsideWorkoutOnID(trening_ID);
							return outsideWorkout;
						}
					}
					ResultSet insideWorkout = null;
					insideWorkout = getInsideWorkoutOnID(trening_ID);
					return insideWorkout;
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
        
        public ResultSet addWorkout(String date, String time, String duration, String performance, String form, int sport_ID, String note) throws Exception {
	        		String queryString = "INSERT INTO trening (dato,tid,varighet,antall_ovelser, prestasjon, personlig_form, idrett_ID, notat) VALUES (?,?,?,?,?,?,?,?)";
				    PreparedStatement prepStat = null;
				    
				    prepStat = connect.prepareStatement(queryString,Statement.RETURN_GENERATED_KEYS);
				    prepStat.setString( 1, date);
				    prepStat.setString( 2, time);
				    prepStat.setString( 3, duration);
				    prepStat.setString( 4, performance);
				    prepStat.setString( 5, form);
				    prepStat.setInt( 	6, sport_ID);
				    prepStat.setString( 7, note);
				    
				    prepStat.executeUpdate();
				    ResultSet generatedKey = null;
				    generatedKey = prepStat.getGeneratedKeys();
				    
				    return generatedKey;	            
        }
        
        public ResultSet addInsideWorkout(String date, String time, String duration, String performance, String form, int sport_ID, String note, String ventilation, int crowd) throws Exception {
        	
        	ResultSet trening_ID = null;
        	trening_ID = addWorkout(date, time, duration, performance, form, sport_ID, note);
        	
        	String queryString = "INSERT INTO inneTrening (ventilasjon, tilskuere) VALUES (?,?)";
		    PreparedStatement prepStat = null;
        	
		    prepStat = connect.prepareStatement(queryString,Statement.RETURN_GENERATED_KEYS);
		    prepStat.setString( 1, ventilation);
		    prepStat.setInt( 2, crowd);
		    prepStat.executeUpdate();
		    
		    return trening_ID;
        }
        
        public ResultSet addOutsideWorkout(String date, String time, String duration, String performance, String form, int sport_ID, String note, String weatherType,int temperature) throws Exception {
        	
        	ResultSet trening_ID = null;
        	trening_ID = addWorkout(date, time, duration, performance, form, sport_ID, note);
        	
        	String queryString = "INSERT INTO inneTrening (vaertype, temperatur) VALUES (?,?)";
		    PreparedStatement prepStat = null;
        	
		    prepStat = connect.prepareStatement(queryString,Statement.RETURN_GENERATED_KEYS);
		    prepStat.setString( 1, weatherType);
		    prepStat.setInt( 2, temperature);
		    prepStat.executeUpdate();
		    
		    return trening_ID;
        }
        
        public ResultSet getAllSports() throws Exception {
        	try {
            		String queryString = "SELECT * FROM idrett";
					statement = connect.createStatement();
					
					ResultSet allSports = null;
					allSports = statement.executeQuery(queryString);
					
					return allSports;
            } 
            catch (Exception e) {
                    throw e;
            }
        }
        
        public ResultSet addSport(String sport) throws Exception {
			PreparedStatement prepStat = null;
			String queryString = "INSERT INTO idrett (navn) VALUES (?)";
				    
			prepStat = connect.prepareStatement(queryString,Statement.RETURN_GENERATED_KEYS);
			prepStat.setString(1, sport);
				    
			prepStat.executeUpdate();
			ResultSet generatedKey = null;
		    generatedKey = prepStat.getGeneratedKeys();
				    
			return generatedKey;        	
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
        	String queryString = "SELECT * FROM ovelse";
        	statement = connect.createStatement();                
                ResultSet exercises = null;
                exercises = statement.executeQuery(queryString);
                return exercises;
        }

}