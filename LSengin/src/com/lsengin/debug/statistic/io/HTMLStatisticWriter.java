package com.lsengin.debug.statistic.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import com.lsengin.debug.statistic.ClassStatistic;
import com.lsengin.debug.statistic.DefaultPackage;
import com.lsengin.debug.statistic.PackageStatistic;
import com.lsengin.debug.statistic.Statistic;
import com.lsengin.debug.statistic.StatisticEntry;

public class HTMLStatisticWriter implements IStatisticWriter {
	protected FileWriter fw = null;
	protected StringBuilder sb = new StringBuilder();
	protected SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy [HH:mm:ss]");
	protected SimpleDateFormat sdf_fl = new SimpleDateFormat("HHmmss");

	protected LinkedList<StatisticEntry> entries = new LinkedList<StatisticEntry>();

	protected long usedClasses = 0;
	protected long usedPackages = 0;
	protected long methodCalles = 0;

	public HTMLStatisticWriter() {

	}

	protected void printHtmlStatistic(String statName, DefaultPackage dp) throws IOException {
		if (!init(statName)) {
			return;
		}

		// TODO init html file

		entries.add(dp);
		while (entries.size() > 0) {
			StatisticEntry se = entries.poll();
			entries.addAll(0, se.getChildren());

			if (se instanceof DefaultPackage) {
				printDefaultPackageStatistic((DefaultPackage) se);
				continue;
			}

			if (se instanceof PackageStatistic) {
				if (((PackageStatistic) se).getClassCount() > 0) {
					printPackageStatistic((PackageStatistic) se);
				}
				continue;
			}

			if (se instanceof ClassStatistic) {
				printClassStatistic((ClassStatistic) se);
			}

		}

		fw.flush();
		fw.close();

		// TODO close html file
	}
	
	protected void printDefaultPackageStatistic(DefaultPackage dp) throws IOException{
		startTable("Root Package", "default");
		
		header(new String[] { "Name", "Value" });
		row(new String[] { "Calls", "" + dp.getCount()});
		row(new String[] { "Packages", "" + dp.getFullPackageCount()});
		row(new String[] { "Classes", "" + dp.getFullClassCount()});
		row(new String[] { "Methods", "" + dp.getFullMethodCount()});

		stopTable();
	}

	protected void printPackageStatistic(PackageStatistic ps) throws IOException {
		startTable("Package", ps.getFullPath());

		header(new String[] { "Name", "Value" });
		row(new String[] { "Subpackages", "" + ps.getPackageCount() });
		row(new String[] { "Classes", "" + ps.getClassCount() });
		
		header(new String[] { "Class", "Methods", "Calls", "Avg. Method Execution Time" });
		for (StatisticEntry se : ps.getChildren()) {
			row(new String[] { se.getName(), se.getMethodCount() + "", se.getCount() + "",
					se.getAvgDurationInMs() + " ms"});
		}

		stopTable();
	}

	protected void printClassStatistic(ClassStatistic cs) throws IOException {
		startTable("Class", cs.getFullPath());

		header(new String[] { "Method", "Calls", "Avg. Duration", "Max. Duration", "Min. Duration" });
		for (StatisticEntry se : cs.getChildren()) {
			row(new String[] { se.getName()+"()", se.getCount() + "", se.getAvgDurationInMs() + " ms",
					se.getMaxDurationInMs() + " ms", se.getMinDurationInMs() + " ms" });
		}

		stopTable();
	}

	protected boolean init(String name) {
		try {
			File file = new File("debug/profiler/statistic/" + name + "_" + sdf_fl.format(new Date()) + ".html");
			file.createNewFile();

			fw = new FileWriter(file);
			fw.write("<style>");
			fw.write(getCSS("debug/tpl/stat_htmlWriter.css", true));
			fw.write("</style>");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	protected void startTable(String type, String name) throws IOException {
		sb.append("<table>");
		sb.append("<caption>" + type + " statistic: " + name + "<span>" + sdf.format(new Date()) + "</span></caption>");

		fw.write(sb.toString());
		sb.setLength(0);
	}

	protected void stopTable() throws IOException {
		sb.append("</table>");

		fw.write(sb.toString());
		sb.setLength(0);
	}

	protected void header(String[] fields) throws IOException {
		sb.append("<tr>");
		for (String s : fields) {
			sb.append("<th>" + s + "</th>");
		}
		sb.append("</tr>");

		fw.write(sb.toString());
		sb.setLength(0);
	}

	protected void row(String[] r) throws IOException {
		sb.append("<tr>");
		for (String s : r) {
			sb.append("<td>" + s + "</td>");
		}
		sb.append("</tr>");

		fw.write(sb.toString());
		sb.setLength(0);
	}

	protected String getCSS(String path, boolean res) throws IOException, URISyntaxException {
		String cssContent = "";
		if (res) {
			cssContent = new String(Files.readAllBytes(
					new File(Thread.currentThread().getContextClassLoader().getResource(path).toURI()).toPath()));
		} else {
			cssContent = new String(Files.readAllBytes(Paths.get(path)));
		}

		return cssContent;
	}

	@Override
	public void print(DefaultPackage dp) {
		try {
			printHtmlStatistic("Default", dp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void print(Statistic s) {
		try {
			printHtmlStatistic(s.getName(), (DefaultPackage) s.getDefaultPackage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
