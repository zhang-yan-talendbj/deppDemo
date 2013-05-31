package com.caribe.stone.jsoup;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.caribe.stone.anki.profile.Office;

public class test {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException, IOException {
		String str = "bond (N-COUNT,<span style=\" font-style: normal; font-weight: normal;\">BUSINESS,junk bond,premium bond</span>)When a government or company issues a bond, it borrows money from investors. The certificate which is issued to investors who lend money is also called a bond. &nbsp;【FIELD标签】：BUSINESS 商<div>Most of it will be financed by government bonds.</div><div><br /></div><div>大部分资金将通过发行政府债券来筹措。</div><div><br /></div><div>...the recent sharp decline in bond prices.</div><div><br /></div><div>最近债券价格的暴跌</div><div><br /></div>";
		System.out.println(str);
		StringBuffer sb = new StringBuffer();
		boolean flag = true;
		for (int i = 0; i < str.length(); i++) {
			char charAt = str.charAt(i);
			if('<'==charAt){
				flag=false;
			}
			if(flag){
				sb.append(charAt);
			}
			if('>'==charAt){
				flag=true;
			}
		}
		System.out.println(sb);

		 List<Card> nDaysCards = WordDemo.getNDaysReviewedCards(2);
		 System.out.println(nDaysCards);
		// System.out.println(System.currentTimeMillis());
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmdd");
		// System.out.println(sdf.parse("20130511000000"));
	}

	private static void getTodayNewCard() {
		long date = System.currentTimeMillis() - 72L * 60 * 60 * 1000;
		System.out.println(date);
		System.out.println(new Date(date));
	}

	private static void getJiongContentFromFile(String word) {
		Office office = new Office();
		StringBuffer sb = new StringBuffer();
		File file = new File("word/" + word.charAt(0) + "/" + word + ".html");
		try {
			Document doc = Jsoup.parse(file, "utf-8");
			Elements select = doc.getElementsByAttributeValue("style",
					"margin-right:5px;font-size:14px/30px;padding:10px 0px;");
			JiongCi ci = new JiongCi();
			Elements eles = doc.getElementsByAttributeValue("style", "max-width:100%;");
			if (eles.size() > 1 && eles.get(0) != null) {
				Element e = eles.get(0);
				String src = e.attr("src");
				if (src.endsWith("png")) {
					String imgName = src.substring(src.lastIndexOf("/") + 1, src.length());
					WordDemo.httpDownload(src, office.getMediaPath() + "/" + imgName);
					ci.setPng(imgName);
					eles.remove(e);
				}
			}

			if (eles.size() != select.size()) {
				System.out.println("eles.size()!=select.size()");
			}

			for (int i = 0; i < select.size(); i++) {
				JiongCiExplain explain = new JiongCiExplain();
				explain.setExplain(select.get(i).text());
				String src = eles.get(i).attr("src");
				String imgName = src.substring(src.lastIndexOf("/") + 1, src.length());
				WordDemo.httpDownload(src, office.getMediaPath() + "/" + imgName);
				// sb.append("<img src=\"").append(imgName).append("\" />");
				// sb.append("<div>").append(select.get(i).text()).append("</div>");
				explain.setImg(imgName);
				ci.getExplainList().add(explain);
			}
			System.out.println(ci.getContent());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void getTodayCards() {
		// 1363068176029
		// 1363068176000
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-hhmmss");
		// System.out.println(sdf.parse("20130318-040000").getTime());
		// WordDemo.setPath(new Home());
		// Connection con = WordDemo.getSqlConnection();
		// String sql =
		// "select r.id,cid,n.sfld from revlog r, notes n, cards c where r.cid=c.id and c.nid=n.id";
		// PreparedStatement stmt = con.prepareStatement(sql);
		// boolean result = stmt.execute();
		// ResultSet rs = stmt.getResultSet();
		// int i=0;
		// ArrayList list = new ArrayList();
		// while (rs.next()) {
		// Long valueOf = Long.valueOf(rs.getString(1));
		// long time = sdf.parse("20130318-040000").getTime();
		// System.out.println(time);
		// if (valueOf > time) {
		// System.out.println(new Date(valueOf));
		// System.out.println(new Date(Long.valueOf(rs.getString(2))));
		// i++;
		// list.add(rs.getString(1));
		// }
		//
		// }
		// System.out.println(i);
		// System.out.println(list.size());
	}
}
