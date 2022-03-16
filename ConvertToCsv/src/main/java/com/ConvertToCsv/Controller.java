package com.ConvertToCsv;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ConvertToCsv.csvHelper.CsvHelper;

@RestController
public class Controller {

	@GetMapping("/hello")
	public String sayHello() {
		return "server is working fine";
	}

	@GetMapping("/download")
	public ResponseEntity<Resource> getFile() throws ClassNotFoundException, IOException, SQLException {

		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String filename = date + "Report.csv";

		InputStreamResource file = new InputStreamResource(CsvHelper.tutorialsToCSV());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/csv")).body(file);
	}

}
