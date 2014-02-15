CREATE TABLE ACCOUNT(ID integer not null GENERATED ALWAYS AS IDENTITY,
				LOGIN varchar(50) not null,
				PSW varchar(50) not null,
				SURNAME varchar(50) not null,
				NAME varchar(50) not null,
				MIDDLENAME varchar(50) not null,
				ACCOUNTROLE smallint not null, 
				ACTIVE boolean not null, primary key (ID));

CREATE TABLE FLIGHT(ID integer not null GENERATED ALWAYS AS IDENTITY,
				DATECREATED timestamp not null,
				FLIGHTNUMBER varchar(50) not null,
				DEPARTURE varchar(50) not null,
				ARRIVAL varchar(50) not null,
				DATEDEPARTURE timestamp not null,
				DATEARRIVAL timestamp not null,
				TICKETAMOUNT integer not null,
				TICKETPRICE double not null,
				TICKETFREEAMOUNT integer not null, primary key (ID));
		
CREATE TABLE RESERVATION (ID integer not null GENERATED ALWAYS AS IDENTITY,
				CUSTOMERSURNAME varchar(50) not null,
				CUSTOMERNAME varchar(50) not null,
				CUSTOMERMIDDLENAME varchar(50) not null,
				CUSTOMEREMAIL varchar(50) not null,
				SUMTOTAL double not null,
				DATERESERVATION timestamp not null,
				DATEPAYMENT timestamp,
				PAID boolean not null,
				primary key (ID));
		
CREATE TABLE TICKET(ID integer not null GENERATED ALWAYS AS IDENTITY,
				FLIGHTID integer constraint FLIGHT_FK references FLIGHT,
				STATUS smallint not null,
				RESERVATIONID integer constraint RESERVATION_FK references RESERVATION,
				primary key (ID));
				
CREATE VIEW SOLDTICKETS(TICKETID, TICKETDATEPAYMENT, TICKETARRIVAL, TICKETPRICE)
				AS SELECT TICKET.ID, DATE(RESERVATION.DATEPAYMENT), FLIGHT.ARRIVAL, FLIGHT.TICKETPRICE
				FROM TICKET
				INNER JOIN FLIGHT ON TICKET.FLIGHTID=FLIGHT.ID
				INNER JOIN RESERVATION ON TICKET.RESERVATIONID=RESERVATION.ID
				WHERE RESERVATION.PAID = TRUE


