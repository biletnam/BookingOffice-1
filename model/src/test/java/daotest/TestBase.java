package daotest;

import java.sql.*;

import org.junit.*;

public abstract class TestBase {
	public static final String UNIT_NAME = "jdbc:derby:..\\BookingOfficeDBTest;create=true";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Connection connection = getConnection();

		Statement stmt = connection.createStatement();
		String command = "create table ACCOUNT(ID integer not null GENERATED ALWAYS AS IDENTITY,"
				+ "LOGIN varchar(50) not null,"
				+ "PSW varchar(50) not null,"
				+ "SURNAME varchar(50) not null,"
				+ "NAME varchar(50) not null,"
				+ "MIDDLENAME varchar(50) not null,"
				+ "ACCOUNTROLE smallint not null,"
				+ "ACTIVE boolean not null," + "primary key (ID))";
		stmt.execute(command);

		command = "create table FLIGHT(ID integer not null GENERATED ALWAYS AS IDENTITY,"
				+ "DATECREATED timestamp not null,"
				+ "FLIGHTNUMBER varchar(50) not null,"
				+ "DEPARTURE varchar(50) not null,"
				+ "ARRIVAL varchar(50) not null,"
				+ "DATEDEPARTURE timestamp not null,"
				+ "DATEARRIVAL timestamp not null,"
				+ "TICKETAMOUNT integer not null,"
				+ "TICKETPRICE double not null,"
				+ "TICKETFREEAMOUNT integer not null," + "primary key (ID))";
		stmt.execute(command);

		command = "create table RESERVATION (ID integer not null GENERATED ALWAYS AS IDENTITY,"
				+ "CUSTOMERSURNAME varchar(50) not null,"
				+ "CUSTOMERNAME varchar(50) not null,"
				+ "CUSTOMERMIDDLENAME varchar(50) not null,"
				+ "CUSTOMEREMAIL varchar(50) not null,"
				+ "SUMTOTAL double not null,"
				+ "DATERESERVATION timestamp not null,"
				+ "DATEPAYMENT timestamp,"
				+ "PAID boolean not null,"
				+ "primary key (ID))";
		stmt.execute(command);

		command = "create table TICKET(ID integer not null GENERATED ALWAYS AS IDENTITY,"
				+ "FLIGHTID integer constraint FLIGHT_FK references FLIGHT,"
				+ "STATUS smallint not null,"
				+ "RESERVATIONID integer constraint RESERVATION_FK references RESERVATION,"
				+ "primary key (ID))";
		stmt.execute(command);
		
		command= "CREATE VIEW SOLDTICKETS(TICKETID, TICKETDATEPAYMENT, TICKETARRIVAL, TICKETPRICE) "
				+ "AS SELECT TICKET.ID, DATE(RESERVATION.DATEPAYMENT), FLIGHT.ARRIVAL, FLIGHT.TICKETPRICE "
				+ "FROM TICKET "
				+ "INNER JOIN FLIGHT ON TICKET.FLIGHTID=FLIGHT.ID "
				+ "INNER JOIN RESERVATION ON TICKET.RESERVATIONID=RESERVATION.ID "
				+ "WHERE RESERVATION.PAID = TRUE";
		
		stmt.execute(command);
		
		command = "INSERT INTO FLIGHT (DATECREATED, FLIGHTNUMBER, DEPARTURE, ARRIVAL, DATEDEPARTURE, DATEARRIVAL, TICKETAMOUNT, TICKETPRICE, TICKETFREEAMOUNT)"
				+ "VALUES ('2013-12-01 10:00:14', 'PS-711', 'Kyiv', 'Stambul', '2013-12-24 06:40:00', '2013-12-24 08:40:00', 10, 1000, 0)";
		stmt.execute(command);

		command = "INSERT INTO FLIGHT (DATECREATED, FLIGHTNUMBER, DEPARTURE, ARRIVAL, DATEDEPARTURE, DATEARRIVAL, TICKETAMOUNT, TICKETPRICE, TICKETFREEAMOUNT)"
				+ "VALUES ('2013-12-01 10:20:00', 'AQ-021', 'Kyiv', 'Roma', '2013-12-25 10:20:00', '2013-12-25 12:10:00', 5, 1500, 0)";
		stmt.execute(command);

		command = "INSERT INTO FLIGHT (DATECREATED, FLIGHTNUMBER, DEPARTURE, ARRIVAL, DATEDEPARTURE, DATEARRIVAL, TICKETAMOUNT, TICKETPRICE, TICKETFREEAMOUNT)"
				+ "VALUES ('2013-12-01 10:30:14', 'TF-140', 'Kyiv', 'New York', '2013-12-28 14:35:00', '2013-12-28 23:55:00', 2, 2000, 0)";
		stmt.execute(command);

		command = "INSERT INTO RESERVATION (CUSTOMERSURNAME, CUSTOMERNAME, CUSTOMERMIDDLENAME, CUSTOMEREMAIL, SUMTOTAL, DATERESERVATION, DATEPAYMENT, PAID)"
				+ "VALUES ('Surname1', 'Name1', 'Middlename1', 'Address1', 10000.00, '2013-12-04 10:00:14', '2013-12-05 10:00:14', TRUE)";
		stmt.execute(command);

		command = "INSERT INTO RESERVATION (CUSTOMERSURNAME, CUSTOMERNAME, CUSTOMERMIDDLENAME, CUSTOMEREMAIL, SUMTOTAL, DATERESERVATION, DATEPAYMENT, PAID)"
				+ "VALUES ('Surname2', 'Name2', 'Middlename2', 'Address2', 15000.00, '2013-12-05 10:00:14', '2013-12-06 10:00:14', TRUE)";
		stmt.execute(command);

		command = "INSERT INTO RESERVATION (CUSTOMERSURNAME, CUSTOMERNAME, CUSTOMERMIDDLENAME, CUSTOMEREMAIL, SUMTOTAL, DATERESERVATION, DATEPAYMENT, PAID)"
				+ "VALUES ('Surname3', 'Name3', 'Middlename3', 'Address3', 20000.00, '2013-12-06 10:00:14', null, FALSE)";
		stmt.execute(command);

		command = "INSERT INTO TICKET (FLIGHTID, STATUS, RESERVATIONID) VALUES (1, 2, 1),"
				+ "(1, 2, 1), (1, 1, 3), (1, 1, 3)";
		stmt.execute(command);

		command = "INSERT INTO TICKET (FLIGHTID, STATUS, RESERVATIONID) VALUES (2, 2, 1),"
				+ "(2, 2, 2), " + "(2, 1, 3)";
		stmt.execute(command);

		command = "INSERT INTO TICKET (FLIGHTID, STATUS, RESERVATIONID) VALUES (3, 2, 2),"
				+ "(3, 2, 2)";
		stmt.execute(command);

		command = "INSERT INTO ACCOUNT (LOGIN, PSW, SURNAME, NAME, MIDDLENAME, ACCOUNTROLE, ACTIVE) VALUES ('Login1', 'Psw1', 'Surname1', 'Name1', 'Middlename1', 0, TRUE),"
				+ "('Login2', 'Psw2', 'Surname2', 'Name2', 'Middlename2', 1, TRUE), ('Login3', 'Psw3', 'Surname3', 'Name3', 'Middlename3', 2, TRUE)";
		stmt.execute(command);

		connection.close();

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Connection connection = getConnection();

		Statement stmt = connection.createStatement();
		String command = "DROP VIEW SOLDTICKETS";
		stmt.execute(command);
		command = "DROP TABLE TICKET";
		stmt.execute(command);
		command = "DROP TABLE RESERVATION";
		stmt.execute(command);
		command = "DROP TABLE FLIGHT";
		stmt.execute(command);
		command = "DROP TABLE ACCOUNT";
		stmt.execute(command);

		connection.close();

	}

	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(UNIT_NAME);
		return connection;
	}
}
