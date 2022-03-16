package com.ConvertToCsv.csvHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

public class CsvHelper {
	public static ByteArrayInputStream tutorialsToCSV() throws IOException, ClassNotFoundException, SQLException {

		CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL)
				.withHeader(DatabaseConnection.getDBConnection());

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);

		csvPrinter.printRecords(DatabaseConnection.getDBConnection());
		csvPrinter.flush();
		csvPrinter.close();
		return new ByteArrayInputStream(out.toByteArray());
	}
}
