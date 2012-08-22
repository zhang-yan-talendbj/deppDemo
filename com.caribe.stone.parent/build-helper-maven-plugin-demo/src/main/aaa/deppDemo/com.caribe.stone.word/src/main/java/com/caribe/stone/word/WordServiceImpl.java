package com.caribe.stone.word;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordServiceImpl implements WordService {
	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	private String jdbcUrl;

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	@Override
	public synchronized List<Word> getReviewWord() {
		H2DB db = new H2DB(jdbcUrl);
		final List<Word> list = new LinkedList<Word>();
		db.execute(new StatementCallback() {

			@Override
			public Object execute(Statement stmt) {
				try {
					// 加载未完成单词
					stmt.execute("select w.word, select Max(cycle_level)  from word_cycle as wc where wc.word=w.word as cl ,select Max(wc.alarm_time) as at from word_cycle as wc where wc.word=w.word as at   from word as w  where w.is_over=0");
					ResultSet rs = stmt.getResultSet();
					while (rs.next()) {
						// rs.getInt if null return 0;
						Word word = new Word(rs.getString(1), rs.getInt(2));
						String dateString = rs.getString(3);
						if (dateString != null) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
							word.setLastReviewTime(sdf.parse(dateString));
						}
						LOG.info(word.toString());
						list.add(word);
					}
					stmt.execute("select sum(done),sum(doing) from (select case when is_over=1 then 1 else 0 end as done, case when is_over=0 then 1 else 0 end as doing from word) as tmp");

					rs = stmt.getResultSet();
					while (rs.next()) {
						System.out.println("Done:" + rs.getString(1) + ",doing:" + rs.getString(2));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return null;
			}
		});
		return list;
	}

	@Override
	public synchronized void insertRecord(final WordThread thread, final boolean flag) {
		H2DB db = new H2DB(jdbcUrl);
		db.execute(new StatementCallback() {

			@Override
			public Object execute(Statement stmt) {
				try {
					// forget
					if (!flag) {
						// 重新开始
						thread.getWord().setCycleLevel(0);
					}
					long time = new Date().getTime();

					String wordContent = thread.getWord().getWordContent();
					Integer cycleLevel = thread.getWord().getCycleLevel();
					Integer newLevel = cycleLevel + 1;

					String sql = "insert into word_cycle(word , cycle_level , alarm_time) values('" + wordContent + "'," + newLevel + ",'"
							+ DateUtil.format(time) + "')";
					LOG.info(sql);
					stmt.execute(sql);
					LOG.info("wordLevel:" + cycleLevel);
					if (cycleLevel == thread.getWordLevelTime().getSize() - 1) {
						String sql2 = "update word set is_over=1, end_time='" + DateUtil.format(new Date()) + "' where word='"
								+ wordContent + "'";
						LOG.info(sql2);
						stmt.execute(sql2);
						LOG.info("Finish :" + thread.getWord());
						App.writeWordToFile(DateUtil.format(new Date()) + ":" + thread.getWord().getWordContent() + "\r\n");
					}
					thread.getWord().setCycleLevel(newLevel);
					thread.getWord().setLastReviewTime(new Date());

				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}
		});

	}

	@Override
	public synchronized Integer getWordLevel(final String word) {
		H2DB db = new H2DB(jdbcUrl);
		Object obj = db.execute(new StatementCallback() {

			@Override
			public Object execute(Statement stmt) {
				int level = 0;
				try {
					// 根据count(*)判断下次复习时间
					String sql = "select max(cycle_level) from word_cycle where word='" + word + "'";
					stmt.execute(sql);
					ResultSet rs = stmt.getResultSet();

					while (rs.next()) {
						// if no recorder then level=0
						level = rs.getInt(1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return level;
			}
		});
		Integer cycleLevel = Integer.parseInt(String.valueOf(obj));
		return cycleLevel;
	}

	@Override
	public void insertWord(final Word words) {
		H2DB db = new H2DB(jdbcUrl);
		db.execute(new StatementCallback() {

			@Override
			public Object execute(Statement stmt) throws SQLException {
				if (words.getCycleLevel() == 0) {
					String sql = "insert into word(word,IS_OVER,BEGIN_TIME) values('" + words.getWordContent() + "',0,'"
							+ DateUtil.format(words.getLastReviewTime()) + "')";
					LOG.info(sql);
					stmt.execute(sql);
				}
				return stmt;
			}
		});

	}
}
