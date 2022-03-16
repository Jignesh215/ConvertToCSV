# ConvertToCSV
This project is simple project for download report from database using sql query. convert it into csv file with header and return it on Rest Endpoint. It does not use any JPA or Hibernate tool.  
Project is created using spring boot and maven. 
As spring starter we need to add spring starter,spring web, Driver(Mssql,mysql,postgrassql,etc),and apache commas library.
Here we don't need any model class to represent data.we directly convert resultset into csv and return it into csv file on rest endpoint.
Database details like url,username, password and driver are in application.property file. please add apropriate details before run.
