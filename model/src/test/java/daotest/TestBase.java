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
				+ "ACTIVE boolean not null," + "primary key (ID))";
		stmt.execute(command);

		command = "create table ACCOUNTRIGHTS(ID integer not null GENERATED ALWAYS AS IDENTITY,"
				+ "ACCOUNTID integer constraint ACCOUNT_FK references ACCOUNT,"
				+ "ACCOUNTROLE smallint not null,"
				+ "ACCOUNTROLEACTIVE boolean not null," + "primary key (ID))";
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
				+ "TICKETFREE integer not null," + "primary key (ID))";
		stmt.execute(command);

		command = "create table RESERVATION (ID integer not null GENERATED ALWAYS AS IDENTITY,"
				+ "CUSTOMERSURNAME varchar(50) not null,"
				+ "CUSTOMERNAME varchar(50) not null,"
				+ "CUSTOMERMIDDLENAME varchar(50) not null,"
				+ "CUSTOMEREMAIL varchar(50) not null,"
				+ "DATERESERVATION timestamp not null,"
				+ "DATEPAYMENT timestamp,"
				+ "PAID boolean not null,"
				+ "primary key (ID))";
		stmt.execute(command);

		command = "create table TICKET(ID integer not null GENERATED ALWAYS AS IDENTITY,"
				+ "FLIGHTID integer constraint FLIGHT_FK references FLIGHT,"
				+ "STATUS smallint not null,"
				+ "RESERVATIONID integer constraint RESERVATION_FK references RESERVATION,"
				+ "DATEPAYMENT timestamp," + "primary key (ID))";
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

		command = "INSERT INTO RESERVATION (CUSTOMERSURNAME, CUSTOMERNAME, CUSTOMERMIDDLENAME, CUSTOMEREMAIL, DATERESERVATION, DATEPAYMENT, ISPAID)"
				+ "VALUES ('Surname1', 'Name1', 'Middlename1', 'Address1', '2013-12-04 10:00:14', '2013-12-05 10:00:14', TRUE)";
		stmt.execute(command);

		command = "INSERT INTO RESERVATION (CUSTOMERSURNAME, CUSTOMERNAME, CUSTOMERMIDDLENAME, CUSTOMEREMAIL, DATERESERVATION, DATEPAYMENT, ISPAID)"
				+ "VALUES ('Surname2', 'Name2', 'Middlename2', 'Address2', '2013-12-05 10:00:14', '2013-12-06 10:00:14', TRUE)";
		stmt.execute(command);

		command = "INSERT INTO RESERVATION (CUSTOMERSURNAME, CUSTOMERNAME, CUSTOMERMIDDLENAME, CUSTOMEREMAIL, DATERESERVATION, DATEPAYMENT, ISPAID)"
				+ "VALUES ('Surname3', 'Name3', 'Middlename3', 'Address3', '2013-12-06 10:00:14', null, FALSE)";
		stmt.execute(command);

		command = "INSERT INTO TICKET (FLIGHTID, STATUS, RESERVATIONID, DATEPAYMENT) VALUES (1, 2, 1, '2013-12-05 10:00:14'),"
				+ "(1, 2, 1, '2013-12-05 10:00:14'), (1, 1, 3, null), (1, 1, 3, null), "
				+ "(1, 0, null, null), (1, 0, null, null), (1, 0, null, null), (1, 0, null, null), (1, 0, null, null), (1, 0, null, null)";
		stmt.execute(command);

		command = "INSERT INTO TICKET (FLIGHTID, STATUS, RESERVATIONID, DATEPAYMENT) VALUES (2, 2, 1, '2013-12-05 10:00:14'),"
				+ "(2, 2, 2, '2013-12-06 10:00:14'), "
				+ "(2, 1, 3, null), "
				+ "(2, 0, null, null), (2, 0, null, null)";
		stmt.execute(command);

		command = "INSERT INTO TICKET (FLIGHTID, STATUS, RESERVATIONID, DATEPAYMENT) VALUES (3, 2, 2, '2013-12-06 10:00:14'),"
				+ "(3, 2, 2, '2013-12-06 10:00:14')";
		stmt.execute(command);

		command = "INSERT INTO ACCOUNT (LOGIN, PSW, SURNAME, NAME, MIDDLENAME, ISACTIVE) VALUES ('Login1', 'Psw1', 'Surname1', 'Name1', 'Middlename1', TRUE),"
				+ "('Login2', 'Psw2', 'Surname2', 'Name2', 'Middlename2', TRUE), ('Login3', 'Psw3', 'Surname3', 'Name3', 'Middlename3', TRUE)";
		stmt.execute(command);

		command = "INSERT INTO ACCOUNTRIGHTS (ACCOUNTID, ACCOUNTROLE) VALUES (1, 0), (1, 1), (2, 2), (3, 3)";
		stmt.execute(command);

		connection.close();

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Connection connection = getConnection();

		Statement stmt = connection.createStatement();
		String command = "drop table TICKET";
		stmt.execute(command);
		command = "drop table RESERVATION";
		stmt.execute(command);
		command = "drop table FLIGHT";
		stmt.execute(command);
		command = "drop table ACCOUNTRIGHTS";
		stmt.execute(command);
		command = "drop table ACCOUNT";
		stmt.execute(command);

		connection.close();

	}

	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(UNIT_NAME);
		return connection;
	}
}
