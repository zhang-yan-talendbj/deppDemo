package com.aiaa.csv;

import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.CSVPrinter;
import com.Ostermiller.util.LabeledCSVParser;

public class CSVDemo {

	@Test
	public void csv() throws Exception {
		LabeledCSVParser lcsvp = new LabeledCSVParser(new CSVParser(new StringReader("Name,Phone\n"
				+ "Stewart,212-555-3233\n" + "Cindy,212-555-8492\n")));

		while (lcsvp.getLine() != null) {
			System.out.println("Name: " + lcsvp.getValueByLabel("Name"));
			System.out.println("Phone: " + lcsvp.getValueByLabel("Phone"));
		}
		assertTrue(true);
	}

	@Test
	public void printer() throws Exception {
		CSVPrinter printer = new CSVPrinter(new FileWriter("rc.csv"));
		String str = "Policy No	Policy Owner	Life Insured	Premium Mode	Benefit	Current Sum Insured	Current Premium	New Sum Insured(after renewal)	New Premium(after renewal)	Paid to Date	Policy Fee	Total Premium After Renewal(Inc. Policy Fee)  ";
		String[] column = str.split("	");
		printer.writeln(column);
		printer.writeln(column);
		printer.writeln(column);
		printer.writeln(column);
		
		List<String> list = new ArrayList<String>();
		list.add("111");
		list.add("111");
		list.add("111");
		list.add("111");
		String[] a = new String[3];
		printer.writeln(list.toArray(a));
	}
	
}
