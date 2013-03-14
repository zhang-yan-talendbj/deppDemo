package com.caribe.stone.jsoup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.ibm.icu.text.SimpleDateFormat;

public class test {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		getTodayCards();
	}

	private static void getTodayCards() throws ClassNotFoundException, SQLException {
		// 1363068176029
		// 1363068176000
		Connection con = WordDemo.getSqlConnection();
		String sql = "update notes set flds= ? where sfld= ?";
		String word = "bother";
		String content = "bother[英] [ˈbɔðə] [美] [ˈbɑðɚ]<div>If something bothers you, or if you bother about it, it worries, annoys, or upsets you. &nbsp;【语法信息】：V n</div><div>【语法信息】：it V n that/wh</div><div>【语法信息】：V about n</div><div>【语法信息】：Also it V n to-inf</div><div>Is something bothering you?...</div><div>你有什么烦心事吗？</div><div><br /></div><div>That kind of jealousy doesn't bother me...</div><div><br /></div><div>那种嫉妒我并不在意。</div><div><br /></div><div>It bothered me that boys weren't interested in me...</div><div><br /></div><div>男孩子们对我不感兴趣令我很烦恼。</div><div><br /></div><div>Never bother about people's opinions.</div><div><br /></div><div>不要在意人们的看法。</div><div><br /></div><div>bothered&nbsp;</div><div>I was bothered about the blister on my hand...</div><div><br /></div><div>手上起的水泡让我心烦。</div><div><br /></div><div>I'm not bothered if he has another child.</div><div><br /></div><div>我不担心他有另一个孩子。</div><div><br /></div>";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, content);
		stmt.setString(2, word);
		boolean result = stmt.execute();
		System.out.println(result);
		}
	}
