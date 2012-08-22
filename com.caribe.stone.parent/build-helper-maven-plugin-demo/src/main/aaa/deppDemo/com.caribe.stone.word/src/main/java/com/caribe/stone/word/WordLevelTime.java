package com.caribe.stone.word;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordLevelTime {
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	private List<Long> timeList;

	public List<Long> getTimeList() {
		return timeList;
	}

	public void setTimeList(List<Long> timeList) {
		this.timeList = timeList;
	}

	public Long isOut(Integer index, Date from, Date to) {
		
		Long time = timeList.get(index);
		if(from==null){
			return time;
		}
		LOG.info("period time:" + time / 1000);
		Date when = new Date(from.getTime() + time);
		LOG.info("from:"+from +",period(s):"+time/1000L+",to:"+to);
		if (to.after(when)) {
			return 0L;

		}
		long l = (from.getTime() + time) - to.getTime();
		return l;
	}

	public Long getIndex(Integer index) {
		return timeList.get(index);
	}

	public int getSize() {
		return timeList.size();
	}
}
